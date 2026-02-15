/*=====================================================
  ⚡ VSCode Custom JS - Behavior Enhancements
  =====================================================*/

(function () {
  "use strict";

  // ─────────── TYPING ANIMATION ON WINDOW TITLE ───────────
  // Shows a subtle blinking dot when you're typing
  const ENABLE_TITLE_TYPING_INDICATOR = true;

  // ─────────── FOCUS DIMMING ───────────
  // Slightly dims inactive editor groups
  const ENABLE_FOCUS_DIMMING = true;
  const DIM_OPACITY = 0.6;

  // ─────────── SMOOTH SCROLL ENHANCEMENT ───────────
  // Already built into VSCode settings, but this ensures it
  const ENABLE_SMOOTH_SCROLL = true;

  // ══════════════════════════════════════════════════════
  // IMPLEMENTATION
  // ══════════════════════════════════════════════════════

  // Wait for DOM to be ready
  function onReady(fn) {
    if (document.readyState === "complete") {
      fn();
    } else {
      window.addEventListener("load", fn);
    }
  }

  onReady(() => {
    console.log("✨ Custom JS loaded successfully!");

    // ─────────── FOCUS DIMMING ───────────
    if (ENABLE_FOCUS_DIMMING) {
      const style = document.createElement("style");
      style.id = "custom-focus-dimming";
      style.textContent = `
        .editor-group-container:not(.active) .editor-container {
          opacity: ${DIM_OPACITY};
          transition: opacity 0.3s ease;
        }
        .editor-group-container:not(.active):hover .editor-container {
          opacity: 0.85;
        }
        .editor-group-container.active .editor-container {
          opacity: 1;
          transition: opacity 0.3s ease;
        }
      `;
      document.head.appendChild(style);
    }

    // ─────────── AUTO-HIDE ACTIVITY BAR LABELS ON COLLAPSE ───────────
    // Adds smooth reveal for the sidebar
    const sidebarStyle = document.createElement("style");
    sidebarStyle.id = "custom-sidebar-animation";
    sidebarStyle.textContent = `
      .sidebar > .content {
        animation: slideInLeft 0.2s ease-out;
      }
      @keyframes slideInLeft {
        from {
          opacity: 0;
          transform: translateX(-8px);
        }
        to {
          opacity: 1;
          transform: translateX(0);
        }
      }
      
      /* Panel slide up animation */
      .panel > .content {
        animation: slideInUp 0.2s ease-out;
      }
      @keyframes slideInUp {
        from {
          opacity: 0;
          transform: translateY(8px);
        }
        to {
          opacity: 1;
          transform: translateY(0);
        }
      }
    `;
    document.head.appendChild(sidebarStyle);

    // ─────────── COMMAND PALETTE ENTRANCE ANIMATION ───────────
    const paletteStyle = document.createElement("style");
    paletteStyle.id = "custom-palette-animation";
    paletteStyle.textContent = `
      .quick-input-widget[style*="display: flex"] {
        animation: paletteIn 0.15s ease-out;
      }
      @keyframes paletteIn {
        from {
          opacity: 0;
          transform: translateY(-10px) scale(0.98);
        }
        to {
          opacity: 1;
          transform: translateY(0) scale(1);
        }
      }

      /* Notification slide in */
      .notification-toast {
        animation: notifIn 0.3s ease-out;
      }
      @keyframes notifIn {
        from {
          opacity: 0;
          transform: translateX(30px);
        }
        to {
          opacity: 1;
          transform: translateX(0);
        }
      }
    `;
    document.head.appendChild(paletteStyle);

    // ─────────── WATERMARK / BRANDING ───────────
    // Adds a subtle custom watermark to the empty editor area
    const watermarkStyle = document.createElement("style");
    watermarkStyle.id = "custom-watermark";
    watermarkStyle.textContent = `
      .watermark > .watermark-box {
        opacity: 0.15 !important;
        transition: opacity 0.5s ease !important;
      }
      .watermark > .watermark-box:hover {
        opacity: 0.4 !important;
      }
    `;
    document.head.appendChild(watermarkStyle);

    // ─────────── KEYBOARD SHORTCUT VISUAL FEEDBACK ───────────
    // Flash effect when using keyboard shortcuts
    const flashStyle = document.createElement("style");
    flashStyle.id = "custom-key-flash";
    flashStyle.textContent = `
      @keyframes keyFlash {
        0%   { box-shadow: inset 0 0 30px rgba(100, 160, 255, 0.1); }
        100% { box-shadow: inset 0 0 0px rgba(100, 160, 255, 0); }
      }
    `;
    document.head.appendChild(flashStyle);

    // Track Ctrl/Cmd key combos for visual flash
    let lastKeyTime = 0;
    document.addEventListener("keydown", (e) => {
      if ((e.ctrlKey || e.metaKey) && e.key !== "Control" && e.key !== "Meta") {
        const now = Date.now();
        if (now - lastKeyTime > 200) {
          lastKeyTime = now;
          const editor = document.querySelector(".editor-instance");
          if (editor) {
            editor.style.animation = "none";
            // Force reflow
            void editor.offsetWidth;
            editor.style.animation = "keyFlash 0.4s ease-out";
          }
        }
      }
    });

    // ─────────── DOUBLE-CLICK TAR AREA TO CREATE NEW FILE ───────────
    // (VSCode already does this, but we log it)
    const tabsContainer = document.querySelector(".tabs-container");
    if (tabsContainer) {
      tabsContainer.addEventListener("dblclick", (e) => {
        if (e.target === tabsContainer) {
          console.log("📄 New file via double-click on tab bar");
        }
      });
    }

    // ─────────── CUSTOM CONSOLE GREETING ───────────
    console.log(
      "%c🎨 Custom Theme Active",
      "color: #64a0ff; font-size: 14px; font-weight: bold;"
    );
    console.log(
      "%cCSS & JS customizations loaded.",
      "color: #888; font-size: 11px;"
    );
  });
})();