(function () {
    "use strict";
  
    const BUTTONS = [
      { action: "close",    icon: "×" },
      { action: "minimize", icon: "−" },
      { action: "maximize", icon: "＋" },
    ];
  
    // Map action to the index of the existing hidden window-icon buttons
    // VS Code order on Linux: minimize(0), maximize(1), close(2)
    const ACTION_TO_INDEX = {
      minimize: 0,
      maximize: 1,
      close: 2,
    };
  
    function performAction(action) {
      // Method 1: Proxy click to the real (hidden) window controls
      const realButtons = document.querySelectorAll(
        ".window-controls-container .window-icon"
      );
      if (realButtons.length) {
        const btn = realButtons[ACTION_TO_INDEX[action]];
        if (btn) {
          btn.style.pointerEvents = "auto";
          btn.click();
          btn.style.pointerEvents = "none";
          return;
        }
      }
  
      // Method 2: Fallback — try Electron IPC directly
      try {
        const { ipcRenderer } = require("electron");
        const channels = {
          close: "vscode:closeWindow",
          minimize: "vscode:minimizeWindow",
          maximize: "vscode:toggleMaximizeWindow",
        };
        ipcRenderer.send(channels[action]);
      } catch (e) {
        console.error("[Traffic Lights] Could not perform action:", action, e);
      }
    }
  
    function createTrafficLights() {
      // Avoid duplicates
      if (document.querySelector(".traffic-lights")) return;
  
      const titlebarLeft = document.querySelector(".titlebar-left");
      if (!titlebarLeft) return;
  
      const container = document.createElement("div");
      container.className = "traffic-lights";
  
      BUTTONS.forEach(({ action, icon }) => {
        const btn = document.createElement("div");
        btn.className = `tl-btn tl-${action}`;
        btn.title = action.charAt(0).toUpperCase() + action.slice(1);
  
        const iconEl = document.createElement("span");
        iconEl.className = "tl-icon";
        iconEl.textContent = icon;
        btn.appendChild(iconEl);
  
        btn.addEventListener("click", (e) => {
          e.preventDefault();
          e.stopPropagation();
          performAction(action);
        });
  
        // Double-click green = maximize (macOS behavior)
        if (action === "maximize") {
          btn.addEventListener("dblclick", (e) => {
            e.preventDefault();
            e.stopPropagation();
            performAction("maximize");
          });
        }
  
        container.appendChild(btn);
      });
  
      // Insert as the first child of the left titlebar area
      titlebarLeft.prepend(container);
      console.log("[Traffic Lights] Injected successfully ✅");
    }
  
    // Poll until the titlebar is ready, then inject
    const interval = setInterval(() => {
      if (document.querySelector(".titlebar-left")) {
        createTrafficLights();
        clearInterval(interval);
      }
    }, 300);
  
    // Also re-inject if VS Code rebuilds the titlebar
    const observer = new MutationObserver(() => {
      if (
        document.querySelector(".titlebar-left") &&
        !document.querySelector(".traffic-lights")
      ) {
        createTrafficLights();
      }
    });
  
    observer.observe(document.documentElement, {
      childList: true,
      subtree: true,
    });
  })();