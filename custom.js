(() => {
  if (window.__LEAPENSE_THEME_V11__) return;
  window.__LEAPENSE_THEME_V11__ = true;

  const BODY_BASE = "lp-theme";
  const BODY_DARK = "lp-dark";
  const BODY_LIGHT = "lp-light";
  const BODY_BLUR = "lp-blurred";
  const BODY_QUICKOPEN = "lp-quickopen-open";

  function detectLightMode() {
    const b = document.body;
    return !!(b && (b.classList.contains("vscode-light") || b.classList.contains("vscode-high-contrast-light")));
  }

  function applyThemeClasses() {
    const b = document.body;
    if (!b) return;
    b.classList.add(BODY_BASE);
    const light = detectLightMode();
    b.classList.toggle(BODY_LIGHT, light);
    b.classList.toggle(BODY_DARK, !light);
  }

  function wireFocusDim() {
    window.addEventListener("blur", () => document.body.classList.add(BODY_BLUR), { passive: true });
    window.addEventListener("focus", () => document.body.classList.remove(BODY_BLUR), { passive: true });
  }

  function wireQuickOpenDim() {
    // Only poll; avoid observing the entire DOM.
    const tick = () => {
      const w = document.querySelector(".quick-input-widget");
      const visible = !!(w && w.offsetParent !== null);
      document.body.classList.toggle(BODY_QUICKOPEN, visible);
    };
    tick();
    setInterval(tick, 500);
  }

  function injectClock() {
    const container =
      document.querySelector(".monaco-workbench .part.statusbar .items-container") ||
      document.querySelector(".monaco-workbench .part.statusbar");
    if (!container) return;
    if (document.querySelector(".lp-clock")) return;

    const item = document.createElement("a");
    item.className = "statusbar-item lp-clock";
    item.style.cursor = "default";
    item.style.userSelect = "none";

    const fmt = () => {
      const d = new Date();
      return `${String(d.getHours()).padStart(2, "0")}:${String(d.getMinutes()).padStart(2, "0")}`;
    };

    const tick = () => {
      item.textContent = fmt();
      item.title = `Local time: ${new Date().toLocaleString()}`;
    };

    tick();
    container.appendChild(item);
    setInterval(tick, 10_000);
  }

  function watchThemeChanges() {
    const b = document.body;
    if (!b) return;
    const mo = new MutationObserver(() => applyThemeClasses());
    mo.observe(b, { attributes: true, attributeFilter: ["class"] });
  }

  function init() {
    if (!document.querySelector(".monaco-workbench")) return false;
    //applyThemeClasses();
    //watchThemeChanges();
    //wireFocusDim();
    //wireQuickOpenDim();
    injectClock();
    return true;
  }

  if (init()) return;
  let tries = 0;
  const t = setInterval(() => {
    tries++;
    if (init() || tries > 60) clearInterval(t);
  }, 250);
})();