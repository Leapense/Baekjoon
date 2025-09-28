document.addEventListener('DOMContentLoaded', () => {
    // ===================== Blur Overlay Logic =====================
    function addBlurOverlay() {
        const editorContent = document.querySelector(
            '.monaco-workbench .part.editor > .content'
        );
        if (!editorContent) return;
        if (!document.getElementById('custom-blur-overlay')) {
            const overlay = document.createElement('div');
            overlay.id = 'custom-blur-overlay';
            overlay.style.position = 'absolute';
            overlay.style.top = '0';
            overlay.style.left = '0';
            overlay.style.right = '0';
            overlay.style.bottom = '0';
            overlay.style.backdropFilter = 'blur(6px)';
            overlay.style.backgroundColor = 'rgba(0,0,0,0.15)';
            overlay.style.zIndex = '10';
            overlay.style.pointerEvents = 'none'; // don't block clicks
            editorContent.appendChild(overlay);

            // Liquid Glass styling on the overlay (purely visual; still non-interactive)
            overlay.classList.add('lg-glass');
        }
    }

    function removeBlurOverlay() {
        const overlay = document.getElementById('custom-blur-overlay');
        if (overlay) overlay.remove();
    }

    function observeQuickInput(quickInput) {
        const observer = new MutationObserver(() => {
            const isVisible = quickInput.style.display !== 'none';
            if (isVisible) {
                addBlurOverlay();
            } else {
                removeBlurOverlay();
            }
        });
        observer.observe(quickInput, { attributes: true, attributeFilter: ['style'] });
    }

    // Poll until the quick-input widget exists, then observe it
    const intervalId = setInterval(() => {
        const quickInput = document.querySelector('.quick-input-widget');
        if (quickInput) {
            if (quickInput.style.display !== 'none') addBlurOverlay();
            observeQuickInput(quickInput);
            clearInterval(intervalId);
        }
    }, 500);

    // ESC anywhere removes blur (just in case)
    document.addEventListener('keydown', (ev) => {
        if (ev.key === 'Escape') removeBlurOverlay();
    });

    // ===================== Theme Token Helper =====================
    function pickThemeTokens() {
        const css = getComputedStyle(document.documentElement);
        const varOr = (name, fallback) => {
            const v = css.getPropertyValue(name).trim();
            return v || fallback;
        };

        const tokens = {
            fg: varOr('--vscode-foreground', ''),
            bg: varOr('--vscode-editor-background', ''),
            border: varOr('--vscode-widget-border', ''),
            subtle: varOr('--vscode-descriptionForeground', ''),
            btnBg: varOr('--vscode-button-background', ''),
            btnFg: varOr('--vscode-button-foreground', ''),
            hoverBg: varOr('--vscode-toolbar-hoverBackground', ''),
        };

        const hasAllCore = tokens.fg && tokens.bg && tokens.btnBg && tokens.btnFg;
        if (hasAllCore) return { tokens, isDark: null };

        // Fallback: detect theme classes anywhere (not only on body)
        const hasClass = (c) => !!document.querySelector('.' + c);
        const isDark =
            hasClass('vs-dark') || hasClass('vscode-dark') || hasClass('hc-black');

        return {
            isDark,
            tokens: {
                fg: tokens.fg || (isDark ? '#e6e6e6' : '#1e1e1e'),
                bg: tokens.bg || (isDark ? 'rgba(0, 0, 0, 0.37)' : 'rgba(255,255,255,0.77)'),
                border: tokens.border || (isDark ? '#3c3c3c' : '#e5e5e5'),
                subtle: tokens.subtle || (isDark ? '#9da0a2' : '#6b6f73'),
                btnBg: tokens.btnBg || '#0e639c',
                btnFg: tokens.btnFg || '#ffffff',
                hoverBg: tokens.hoverBg || (isDark ? '#2a2a2a' : '#f3f3f3'),
            },
        };
    }

    // ===================== Liquid Glass Helpers =====================
    function ensureLiquidGlassStyles() {
        if (document.getElementById('custom-liquid-glass-styles')) return;
        const style = document.createElement('style');
        style.id = 'custom-liquid-glass-styles';
        style.textContent = `
      /* Base glass panel */
      .lg-glass {
        font-family: "Apple SD Gothic Neo", "SF Pro Display","MesloLGS NF", "Fira Code Nerd Font", "Fira Code", monospace !important;
        position: relative;
        border: 1px solid rgba(255, 255, 255, 0.18);
        border-radius: 12px;
        backdrop-filter: blur(14px) saturate(1.2);
        -webkit-backdrop-filter: blur(14px) saturate(1.2);
        overflow: hidden;
        box-shadow:
          inset 0 1px 0 rgba(255,255,255,0.15),
          inset 0 -1px 0 rgba(0,0,0,0.08),
          0 20px 60px rgba(0,0,0,0.25), 0 2px 12px rgba(0,0,0,0.2);
      }
      .lg-glass::before {
        content: "";
        position: absolute; inset: 0;
        pointer-events: none;
        mix-blend-mode: screen;
      }
      .lg-spec {
        position: absolute;
        filter: blur(18px);
        pointer-events: none;
        opacity: .7;
      }
      @keyframes lgSweep {
        0%   { transform: rotate(0deg)   scale(1.05); }
        50%  { transform: rotate(180deg) scale(1.10); }
        100% { transform: rotate(360deg) scale(1.05); }
      }
      .lg-caustics {
        position: absolute;
        pointer-events: none;
        opacity: .22;
        filter: blur(10px) contrast(1.05) saturate(1.1);
      }
      @keyframes lgFloat {
        0%   { transform: translate3d(0, 0, 0) scale(1.0); }
        25%  { transform: translate3d(-3%, 2%, 0) scale(1.03); }
        50%  { transform: translate3d(2%, -3%, 0) scale(1.02); }
        75%  { transform: translate3d(-2%, -1%, 0) scale(1.04); }
        100% { transform: translate3d(0, 0, 0) scale(1.0); }
      }
      .lg-grain {
        position: absolute; inset: 0;
        pointer-events: none;
        mix-blend-mode: soft-light;
        opacity: .07;
      }
      @keyframes lgGrainShift {
        0%   { transform: translate3d(0,0,0); }
        100% { transform: translate3d(0,-2px,0); }
      }
    `;
        document.head.appendChild(style);
    }

    /**
     * Apply liquid glass layers to an element.
     * Respects VS Code theme tokens if available.
     * @param {HTMLElement} el
     * @param {{bg?:string, border?:string}} tokens
     */
    function applyLiquidGlass(el, tokens = {}) {
        ensureLiquidGlassStyles();
        el.classList.add('lg-glass');

        // Match VS Code theme background/border softly
        //if (tokens.bg) {
        //  el.style.background = 'color-mix(in oklab, ' + tokens.bg + ' 75%, transparent)';
        //}
        if (tokens.bg) {
            el.style.background = tokens.bg;
        }
        if (tokens.border) {
            el.style.border = '1px solid ' + tokens.border;
        }

        // Add animated light layers
        const spec = document.createElement('div');
        spec.className = 'lg-spec';
        const caustics = document.createElement('div');
        caustics.className = 'lg-caustics';
        const grain = document.createElement('div');
        grain.className = 'lg-grain';

        el.appendChild(spec);
        el.appendChild(caustics);
        el.appendChild(grain);
    }

    // ===================== Welcome Modal =====================
    (function welcomeModalBootstrap() {
        const STORAGE_KEY = 'vscodeCustomWelcome.dismissed';
        const SHOW_ON_EVERY_START = false; // set true to always show

        if (!SHOW_ON_EVERY_START && localStorage.getItem(STORAGE_KEY) === 'true')
            return;

        createWelcomeModal();

        function createWelcomeModal() {
            if (document.getElementById('custom-welcome-overlay')) return;

            const { tokens, isDark } = pickThemeTokens();

            // Backdrop overlay
            const overlay = document.createElement('div');
            overlay.id = 'custom-welcome-overlay';
            overlay.setAttribute('role', 'dialog');
            overlay.setAttribute('aria-modal', 'true');
            Object.assign(overlay.style, {
                position: 'fixed',
                inset: '0',
                background: 'rgba(0,0,0,0.35)',
                backdropFilter: 'blur(2px)',
                zIndex: '10000',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
            });

            // Modal panel
            const modal = document.createElement('div');
            Object.assign(modal.style, {
                minWidth: '420px',
                maxWidth: '560px',
                padding: '20px 22px',
                borderRadius: '12px',
                background: tokens.bg || (isDark ? 'rgba(0, 0, 0, 0.37)' : 'rgba(255,255,255,0.06)'),
                color: tokens.fg || '#e6e6e6',
                boxShadow:
                    '0 20px 60px rgba(0,0,0,0.25), 0 2px 12px rgba(0,0,0,0.2)',
                border: `1px solid ${tokens.border || 'rgba(255,255,255,0.18)'}`,
                fontFamily:
                    'var(--vscode-font-family, system-ui, -apple-system, Segoe UI, Roboto, sans-serif)',
            });

            // Apply Liquid Glass to modal
            applyLiquidGlass(modal, tokens);

            // Title
            const h = document.createElement('div');
            h.textContent = 'Welcome 👋';
            Object.assign(h.style, {
                fontSize: '18px',
                fontWeight: '600',
                marginBottom: '8px',
            });

            // Body
            const p = document.createElement('div');
            p.innerHTML =
                '커스텀 CSS/JS가 활성화되었습니다. 명령 팔레트(⌘/Ctrl+Shift+P)를 열면 에디터가 살짝 블러 처리돼 초점이 팔레트에 맞춰집니다. ' +
                '문제가 생기면 “Disable Custom CSS and JS” 명령으로 비활성화하세요.';
            Object.assign(p.style, {
                fontSize: '13px',
                lineHeight: '1.6',
                color: tokens.subtle || (isDark ? '#9da0a2' : '#6b6f73'),
            });

            // Footer
            const footer = document.createElement('div');
            Object.assign(footer.style, {
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                marginTop: '16px',
                gap: '12px',
            });

            // Checkbox
            const left = document.createElement('label');
            Object.assign(left.style, {
                display: 'inline-flex',
                alignItems: 'center',
                gap: '8px',
                userSelect: 'none',
            });
            const cb = document.createElement('input');
            cb.type = 'checkbox';
            cb.id = 'custom-welcome-dontshow';
            const lbl = document.createElement('span');
            lbl.textContent = '다시 보지 않기';
            left.appendChild(cb);
            left.appendChild(lbl);

            // Actions
            const actions = document.createElement('div');
            Object.assign(actions.style, { display: 'inline-flex', gap: '8px' });

            const mkBtn = (text, primary = false) => {
                const b = document.createElement('button');
                b.type = 'button';
                b.textContent = text;
                Object.assign(b.style, {
                    fontSize: '13px',
                    padding: '8px 12px',
                    borderRadius: '8px',
                    border: `1px solid ${primary ? 'transparent' : (tokens.border || 'transparent')}`,
                    cursor: 'pointer',
                });
                if (primary) {
                    b.style.background = tokens.btnBg || '#0e639c';
                    b.style.color = tokens.btnFg || '#ffffff';
                } else {
                    b.style.background = 'transparent';
                    b.style.color = tokens.fg || '#e6e6e6';
                    b.onmouseenter = () => {
                        b.style.background = tokens.hoverBg || (isDark ? '#2a2a2a' : '#f3f3f3');
                    };
                    b.onmouseleave = () => { b.style.background = 'transparent'; };
                }
                return b;
            };

            const btnDocs = mkBtn('문서 열기');
            const btnClose = mkBtn('닫기', true);

            actions.appendChild(btnDocs);
            actions.appendChild(btnClose);
            footer.appendChild(left);
            footer.appendChild(actions);

            // Assemble
            modal.appendChild(h);
            modal.appendChild(p);
            modal.appendChild(footer);
            overlay.appendChild(modal);
            document.body.appendChild(overlay);

            // Focus trap
            const focusable = [cb, btnDocs, btnClose];
            let lastFocused = document.activeElement;
            cb.focus();
            overlay.addEventListener('keydown', (e) => {
                if (e.key === 'Tab') {
                    const idx = focusable.indexOf(document.activeElement);
                    if (e.shiftKey) {
                        if (idx <= 0) {
                            focusable[focusable.length - 1].focus();
                            e.preventDefault();
                        }
                    } else {
                        if (idx === focusable.length - 1) {
                            focusable[0].focus();
                            e.preventDefault();
                        }
                    }
                } else if (e.key === 'Escape') {
                    close(true);
                }
            });

            // Buttons
            btnClose.addEventListener('click', () => close(true));
            btnDocs.addEventListener('click', () => {
                window.open(
                    'https://code.visualstudio.com/docs/getstarted/themes#_customizing-a-color-theme',
                    '_blank',
                    'noopener'
                );
            });

            // Click outside closes
            overlay.addEventListener('click', (e) => {
                if (e.target === overlay) close(true);
            });

            function close(setStorage) {
                if (setStorage && cb.checked) {
                    try { localStorage.setItem(STORAGE_KEY, 'true'); } catch { }
                }
                overlay.remove();
                if (lastFocused && typeof lastFocused.focus === 'function') lastFocused.focus();
            }
        }
    })();

    // ===================== Editor Music Player (robust mount + local playlist + speed + pitch toggle) =====================
    (function editorMusicPlayer() {
        const ID = 'editor-music-player';
        if (document.getElementById(ID)) return;

        const STORAGE = {
            vol: 'vscodeAudioPlayer.volume',
            idx: 'vscodeAudioPlayer.trackIndex',
            collapsed: 'vscodeAudioPlayer.collapsed',
            listOpen: 'vscodeAudioPlayer.listOpen',
            rate: 'vscodeAudioPlayer.playbackRate',
            pitch: 'vscodeAudioPlayer.pitchPreserve', // true=피치 고정, false=해제(기본)
            playflow: 'vscodeAudioPlayer.playflow',
            highlight: 'vscodeAudioPlayer.highlight',
            eqGains: 'vscodeAudioPlayer.eqGains',
        };

        let audioContext = null;
        let audioSourceNode = null;
        let eqFilters = [];
        let isAudioGraphSetup = false;

        const REMOTE_PRESETS = [
        ];

        // 1) 에디터 컨테이너 찾기(여러 후보) + 실패 시 고정(fixed)로 바디/워크벤치에 부착
        function findEditorContainer() {
            return (
                document.querySelector('.monaco-workbench .part.editor > .content') ||
                document.querySelector('.monaco-workbench .part.editor .content') ||
                document.querySelector('.monaco-workbench .part.editor') ||
                null
            );
        }

        let tries = 0;
        const maxTriesBeforeFallback = 20; // 약 10초
        const t = setInterval(tryMount, 500);
        tryMount();

        function tryMount() {
            if (document.getElementById(ID)) {
                clearInterval(t);
                return;
            }
            const container = findEditorContainer();
            if (container) {
                try {
                    createMusicPlayer(container, /*useFixed*/ false);
                    console.info('[MusicPlayer] mounted on', describe(container));
                    clearInterval(t);
                } catch (e) {
                    console.error('[MusicPlayer] init error (will retry):', e);
                    // 재시도 유지
                }
            } else {
                tries++;
                if (tries >= maxTriesBeforeFallback) {
                    // fallback: workbench/body에 고정 위치로
                    const wb = document.querySelector('.monaco-workbench') || document.body;
                    try {
                        createMusicPlayer(wb, /*useFixed*/ true);
                        console.warn('[MusicPlayer] fallback: mounted fixed on workbench/body');
                    } catch (e) {
                        console.error('[MusicPlayer] fallback init error:', e);
                    } finally {
                        clearInterval(t);
                    }
                }
            }
        }

        function describe(el) {
            return el.id ? `#${el.id}` : el.className ? `.${String(el.className).split(' ').join('.')}` : el.tagName;
        }

        function createMusicPlayer(root, useFixed) {
            const { tokens, isDark } = pickThemeTokens();

            // 테마 기반 공통 스타일 주입
            function injectPlayerStyles(tokens, isDark) {
                const id = 'editor-music-player-theme';
                let style = document.getElementById(id);
                const accent = tokens.btnBg || '#0e639c';
                const fg = tokens.fg || (isDark ? '#e6e6e6' : '#1e1e1e');
                const bg = tokens.bg || (isDark ? '#252526' : '#ffffff');
                const border = tokens.border || (isDark ? '#3c3c3c' : '#e5e5e5');
                const hover = tokens.hoverBg || (isDark ? '#2a2a2a' : '#f3f3f3');
                const subtle = tokens.subtle || (isDark ? '#9da0a2' : '#6b6f73');
                const caretFill = encodeURIComponent(isDark ? '#cfd3d7' : '#4a4d50');

                const css = `
          #editor-music-player button,
          #editor-music-player select {
            background: color-mix(in oklab, ${bg} 88%, transparent);
            color: ${fg};
            border: 1px solid ${border};
            outline: none;
          }
          #editor-music-player button:hover,
          #editor-music-player select:hover {
            background: ${hover};
          }
          #editor-music-player button:focus-visible,
          #editor-music-player select:focus-visible,
          #editor-music-player input[type="range"]:focus-visible {
            outline: none;
            border-color: ${accent};
            box-shadow: 0 0 0 2px ${accent}55;
          }
          #editor-music-player select {
            -webkit-appearance: none;
            appearance: none;
            padding: 5px 26px 5px 8px;
            border-radius: 8px;
            background-color: ${bg};
            color: ${fg};
            background-image: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 12 8'><path fill='${caretFill}' d='M1 1l5 5 5-5'/></svg>");
            background-repeat: no-repeat;
            background-position: right 8px center;
            background-size: 12px 8px;
          }
          #editor-music-player button {
            border-radius: 8px;
            color: ${fg};
          }
          #editor-music-player input[type="range"] {
            -webkit-appearance: none;
            appearance: none;
            height: 18px;
            background: transparent;
            position: relative;
          }
          #editor-music-player input[type="range"]::-webkit-slider-runnable-track {
            height: 6px;
            border-radius: 999px;
            background: ${bg};
            backdrop-filter: blur(8px);
            border: 1px solid rgba(255,255,255,0.12);
            box-shadow: inset 0 1px 0 rgba(255,255,255,0.1),
                        inset 0 -1px 0 rgba(0,0,0,0.1),
                        0 2px 4px rgba(0,0,0,0.15);
            position: relative;
          }
          /* macOS 스타일 슬라이더 */
        #editor-music-player input[type="range"]::-webkit-slider-thumb {
        -webkit-appearance: none;
        appearance: none;
        width: 16px;
        height: 16px;
        margin-top: -6px;
        border-radius: 50%;
        background: ${accent};
        cursor: pointer;
        transition: all 0.15s ease;
        }

        #editor-music-player input[type="range"]::-webkit-slider-thumb:hover {
        transform: scale(1.2);
        }

        #editor-music-player input[type="range"]::-webkit-slider-runnable-track {
            height: 6px;
            border-radius: 2px;
            background: ${bg};
        }

        /* Firefox 지원 */
        #editor-music-player input[type="range"]::-moz-range-thumb {
        width: 16px;
        height: 16px;
        border-radius: 50%;
        border: none;
        background: ${accent};
        cursor: pointer;
        }

        #editor-music-player input[type="range"]::-moz-range-track {
        height: 6px;
        border-radius: 2px;
        background: ${bg};
        }

        /* 진행 표시 (webkit) */
        #editor-music-player input[type="range"]::-webkit-slider-runnable-track {
        background-image: linear-gradient(
            to right,
            ${accent} 0%,
            ${accent} var(--progress, 0%),
            #d1d1d6 var(--progress, 0%),
            #d1d1d6 100%
        );
        }

        /* 진행 표시 (Firefox) */
        #editor-music-player input[type="range"]::-moz-range-progress {
        height: 4px;
        border-radius: 2px;
        background: ${accent};
        box-shadow: 
            inset 0 1px 2px rgba(0, 0, 0, 0.1),
            0 1px 0 rgba(255, 255, 255, 0.5);
        }

        /* 포커스 상태 */
        #editor-music-player input[type="range"]:focus {
        outline: none;
        }
          input[role="switch"] {
            appearance: none;
            -webkit-appearance: none;
            position: relative;
            width: 38px; height: 22px;
            border: 1px solid ${border};
            border-radius: 999px;
            background-color: color-mix(in oklab, ${bg} 60%, black);
            transition: background-color .2s ease-in-out;
            cursor: pointer;
          }
          input[role="switch"]::before {
            content: '';
            position: absolute;
            top: 2px;
            left: 2px;
            width: 16px;
            height: 16px;
            border-radius: 50%;
            background-color: ${fg};
            transition: transform .2s ease-in-out;
          }
          input[role="switch"]:checked {
            background-color: ${accent};
            border-color: ${accent};
          }
          input[role="switch"]:checked::before {
            transform: translateX(16px);
          }
          #editor-music-player-list-wrapper::-webkit-scrollbar {
            width: 8px;
          }
          #editor-music-player-list-wrapper::-webkit-scrollbar-track {
            background: transparent;
          }
          #editor-music-player-list-wrapper::-webkit-scrollbar-thumb {
            background-color: color-mix(in oklab, ${subtle} 50%, transparent);
            border-radius: 4px;  
          }
          #editor-music-player-list-wrapper::-webkit-scrollbar-thumb:hover {
            background-color: color-mix(in oklab, ${subtle} 70%, transparent);
          }
          #editor-music-player-list-wrapper::-webkit-scrollbar-button {
            display: none;
          }
          #editor-music-player .emp-collapsible {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            overflow: hidden;
            max-width: var(--emp-open-w, 800px);
            opacity: 1;
            transform: translateX(0);
            transition: max-width 220ms cubic-bezier(.2, .7, .2, 1), opacity 160ms ease, transform 220ms cubic-bezier(.2, .7, .2, 1);
          }
          #editor-music-player.is-collapsed .emp-collapsible {
            max-width: 0;
            opacity: 0;
            transform: translateX(6px);
            pointer-events: none;
          }
          #editor-music-player.is-collapsed .emp-collapsible input,
          #editor-music-player.is-collapsed .emp-collapsible button,
          #editor-music-player.is-collapsed .emp-collapsible select {
            pointer-events: none;
          }
          @media (prefers-reduced-motion: reduce) {
            #editor-music-player .emp-collapsible {
              transition: none;
            }
          }
        `;
                if (!style) {
                    style = document.createElement('style');
                    style.id = id;
                    document.head.appendChild(style);
                }
                style.textContent = css;
            }
            injectPlayerStyles(tokens, isDark);

            injectProgressStyles(tokens, isDark);

            // Wrapper
            const wrap = document.createElement('div');
            wrap.id = ID;
            Object.assign(wrap.style, {
                position: useFixed ? 'fixed' : 'absolute',
                top: '40px',
                right: '20px',
                zIndex: '30',
                pointerEvents: 'auto',
                userSelect: 'none',
                maxWidth: 'min(500px, calc(100% - 40px))',
            });

            // Panel (glass)
            const panel = document.createElement('div');
            Object.assign(panel.style, {
                display: 'inline-flex',
                alignItems: 'center',
                gap: '8px',
                padding: '6px 8px',
                borderRadius: '10px',
                color: tokens.fg || '#e6e6e6',
                position: 'relative',
                maxWidth: '100%',
            });
            applyLiquidGlass(panel, tokens);

            const playerContainer = document.createElement('div');
            playerContainer.id = 'player-container';
            panel.appendChild(playerContainer);
            const row = document.createElement('div');
            Object.assign(row.style, {
                display: 'inline-flex',
                alignItems: 'center',
                gap: '8px',
                position: 'relative',
                zIndex: '1',
                maxWidth: '100%',
            });

            const title = document.createElement('span');
            title.title = '현재 트랙';
            Object.assign(title.style, {
                fontSize: '12px',
                opacity: '0.9',
                maxWidth: '260px',
                overflow: 'hidden',
                textOverflow: 'ellipsis',
                whiteSpace: 'nowrap',
            });

            const audio = document.createElement('audio');
            audio.id = 'myAudio';
            audio.preload = 'metadata';
            audio.style.display = 'none';
            audio.crossOrigin = 'anonymous';

            function setupEqualizerGraph(mediaElement) {
                if (isAudioGraphSetup) return;

                try {
                    const context = new (window.AudioContext || window.webkitAudioContext)();
                    const sourceNode = context.createMediaElementSource(mediaElement);

                    const frequencies = [
                        25, 31, 40, 50, 63, 80, 100, 125, 160, 200, 250, 315, 400, 500, 630, 800,
                        1000, 1250, 1600, 2000, 2500, 3150, 4000, 5000, 6300, 8000, 10000, 12500,
                        16000, 20000
                    ];

                    const savedGains = JSON.parse(localStorage.getItem(STORAGE.eqGains)) || [];

                    const filters = frequencies.map((frequency, index) => {
                        const filterNode = context.createBiquadFilter();
                        if (index === 0) {
                            filterNode.type = 'lowshelf';
                        } else if (index === frequencies.length - 1) {
                            filterNode.type = 'highshelf';
                        } else {
                            filterNode.type = 'peaking';
                            filterNode.Q.value = 1.41;
                        }
                        filterNode.frequency.value = frequency;
                        filterNode.gain.value = savedGains[index] || 0;
                        return filterNode;
                    });

                    sourceNode.connect(filters[0]);
                    for (let i = 0; i < filters.length - 1; i++) {
                        filters[i].connect(filters[i + 1]);
                    }
                    filters[filters.length - 1].connect(context.destination);

                    audioContext = context;
                    audioSourceNode = sourceNode;
                    eqFilters = filters;
                    isAudioGraphSetup = true;

                    console.info('[MusicPlayer] Equalizer audio graph initialized.');
                } catch (e) {
                    console.error('[MusicPlayer] Failed to initialize equalizer audio graph:', e);
                }
            }
            setupEqualizerGraph(audio);

            const mkBtn = (text, aria, titleText) => {
                const b = document.createElement('button');
                b.type = 'button';
                b.textContent = text;
                b.setAttribute('aria-label', aria || text);
                if (titleText) b.title = titleText;
                Object.assign(b.style, {
                    fontSize: '13px',
                    padding: '6px 8px',
                    borderRadius: '8px',
                    border: '1px solid ' + (tokens.border || 'rgba(255,255,255,0.18)'),
                    background: 'transparent',
                    color: tokens.fg || '#e6e6e6',
                    cursor: 'pointer',
                    lineHeight: '1',
                });
                b.onmouseenter = () => {
                    b.style.background = tokens.hoverBg || (isDark ? '#2a2a2a' : '#f3f3f3');
                };
                b.onmouseleave = () => {
                    b.style.background = 'transparent';
                };
                return b;
            };

            const prevBtn = mkBtn('⏮', '이전 곡', '이전 트랙');
            const playBtn = mkBtn('▶', '재생/일시정지', '재생/일시정지');
            const nextBtn = mkBtn('⏭', '다음 곡', '다음 트랙');
            const addBtn = mkBtn('＋', '파일 추가', '오디오 파일 추가');
            const addDirBtn = mkBtn('📁', '폴더 추가', '폴더에서 오디오 추가');
            const listBtn = mkBtn('♪', '재생목록 열기/닫기', '재생목록 열기/닫기');
            const collapseBtn = mkBtn('–', '접기/펼치기', '접기/펼치기');
            const settingsBtn = mkBtn('⚙️', '설정', '플레이어 설정');

            const vol = document.createElement('input');
            vol.type = 'range';
            vol.min = '0'; vol.max = '1'; vol.step = '0.01';
            vol.value = localStorage.getItem(STORAGE.vol) || '0.7';
            Object.assign(vol.style, {
                width: '50px',
                cursor: 'pointer',
            });
            vol.style.setProperty('accent-color', tokens.btnBg || '#0e639c');

            setupVolumeTooltip(vol, wrap, tokens);

            function setupVolumeTooltip(vol, anchor, tokens) {
                const TIP_ID = 'emp-vol-tooltip';
                let tip = document.getElementById(TIP_ID);
                if (!tip) {
                    tip = document.createElement('div');
                    tip.id = TIP_ID;
                    Object.assign(tip.style, {
                        position: 'absolute',
                        left: '0', top: '0',
                        transform: 'translate(-50%, -100%)',
                        padding: '4px 8px',
                        borderRadius: '6px',
                        fontSize: '11px',
                        pointerEvents: 'none',
                        opacity: '0',
                        transition: 'opacity .15s ease',
                        whiteSpace: 'nowrap',
                        zIndex: '9999',
                        background: tokens.bg ? `color-mix(in oklab, ${tokens.bg} 88%, transparent)` : 'rgba(0,0,0,0.8)',
                        color: tokens.fg || '#fff',
                        border: `1px solid ${tokens.border || 'rgba(255,255,255,0.15)'}`,
                        backdropFilter: 'blur(8px)',
                        boxShadow: '0 6px 20px rgba(0,0,0,0.25)',
                    });
                    anchor.appendChild(tip);
                }

                const THUMB = 16;
                const show = () => { tip.style.opacity = '1'; };
                const hide = () => { tip.style.opacity = '0'; };
                const update = () => {
                    const v = parseFloat(vol.value);
                    const min = parseFloat(vol.min || '0');
                    const max = parseFloat(vol.max || '1');
                    const p = Math.min(1, Math.max(0, (v - min) / (max - min || 1)));

                    const inRect = vol.getBoundingClientRect();
                    const aRect = anchor.getBoundingClientRect();

                    const xInInput = THUMB / 2 + p * (inRect.width - THUMB);
                    const x = (inRect.left - aRect.left) + xInInput;

                    const y = (inRect.top - aRect.top) - 14;

                    tip.style.transform = `translate(${x}px, ${y}px) translate(-50%, -4px)`;
                    tip.textContent = `${Math.round(v * 100)}%`;
                };

                const onEnter = () => { update(); show(); };
                const onLeave = () => { hide(); };


                row.appendChild(vol);
                vol.addEventListener('input', () => { update(); show(); });
                vol.addEventListener('mouseenter', onEnter);
                vol.addEventListener('mouseleave', onLeave);
                vol.addEventListener('focus', onEnter);
                vol.addEventListener('blur', onLeave);
                window.addEventListener('resize', update);
            }

            // === Playback Speed (0.5×~2.0×) ===
            const speedContainer = document.createElement('div');
            Object.assign(speedContainer.style, {
                display: 'inline-flex',
                alignItems: 'center',
                gap: '6px',
            });
            speedContainer.title = '재생 속도 (더블클릭으로 초기화)';

            const speedSlider = document.createElement('input');
            speedSlider.type = 'range';
            speedSlider.min = '0.5';
            speedSlider.max = '2.0';
            speedSlider.step = '0.01';

            const savedRate = parseFloat(localStorage.getItem(STORAGE.rate) || '1.0');
            speedSlider.value = String(savedRate);
            Object.assign(speedSlider.style, {
                width: '80px',
                cursor: 'pointer',
            });

            const speedValueDisplay = document.createElement('span');
            Object.assign(speedValueDisplay.style, {
                fontSize: '11px',
                fontVariantNumeric: 'tabular-nums',
                width: '38px',
                textAlign: 'center',
                opacity: '0.9',
                cursor: 'pointer',
            });

            function updateSpeedUIAndAudio() {
                const rate = parseFloat(speedSlider.value);
                speedValueDisplay.textContent = rate.toFixed(2) + 'x';
                audio.playbackRate = rate;
                audio.defaultPlaybackRate = rate;
                localStorage.setItem(STORAGE.rate, String(rate));
            }

            speedSlider.addEventListener('input', updateSpeedUIAndAudio);
            speedContainer.addEventListener('dblclick', () => {
                speedSlider.value = '1.0';
                updateSpeedUIAndAudio();
            });

            speedContainer.appendChild(speedSlider);
            speedContainer.appendChild(speedValueDisplay);

            // === Pitch preserve toggle (기본: 꺼짐) ===
            let pitchPreserve = JSON.parse(localStorage.getItem(STORAGE.pitch) || 'false');
            function setPitchPreserve(preserve) {
                ['preservesPitch', 'mozPreservesPitch', 'webkitPreservesPitch'].forEach((p) => {
                    if (p in audio) { try { audio[p] = preserve; } catch { } }
                });
                localStorage.setItem(STORAGE.pitch, String(!!preserve));
            }
            setPitchPreserve(pitchPreserve);
            const pitchBtn = mkBtn(pitchPreserve ? '🔒' : '🔓', '피치 보정 토글', '피치 보정(🔒=고정, 🔓=해제)');
            function updatePitchBtn() { pitchBtn.textContent = pitchPreserve ? '🔒' : '🔓'; }
            pitchBtn.addEventListener('click', () => {
                pitchPreserve = !pitchPreserve;
                setPitchPreserve(pitchPreserve);
                updatePitchBtn();
            });


            settingsBtn.addEventListener('click', () => {
                showSettingsModal();
            });

            function showSettingsModal() {
                if (document.getElementById('custom-settings-overlay')) return;
                const overlay = document.createElement('div');
                overlay.id = 'custom-settings-overlay';
                overlay.setAttribute('role', 'dialog');
                overlay.setAttribute('aria-modal', 'true');
                Object.assign(overlay.style, {
                    position: 'fixed',
                    inset: '0',
                    background: 'rgba(0,0,0,0.35)',
                    backdropFilter: 'blur(2px)',
                    zIndex: '10000',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                });

                const modal = document.createElement('div');
                Object.assign(modal.style, {
                    minWidth: '420px',
                    maxWidth: '960px',
                    padding: '20px 22px',
                    borderRadius: '12px',
                    background: tokens.bg || 'rgba(255,255,255,0.06)',
                    color: tokens.fg || '#e6e6e6',
                    boxShadow:
                        '0 20px 60px rgba(0,0,0,0.25), 0 2px 12px rgba(0,0,0,0.2)',
                    border: `1px solid ${tokens.border || 'rgba(255,255,255,0.18)'}`,
                    fontFamily: 'var(--vscode-font-family, system-ui, -apple-system, Segoe UI, Roboto, sans-serif)',
                    overflowX: 'auto',
                });

                applyLiquidGlass(modal, tokens);

                const h = document.createElement('div');
                h.textContent = '플레이어 설정';
                Object.assign(h.style, {
                    fontSize: '18px',
                    fontWeight: '600',
                    marginBottom: '16px',
                });

                const content = document.createElement('div');
                Object.assign(content.style, {
                    display: 'flex',
                    flexDirection: 'column',
                    gap: '16px',
                });

                const playflowFieldset = document.createElement('fieldset');
                Object.assign(playflowFieldset.style, {
                    border: `1px solid ${tokens.border || 'rgba(255,255,255,0.18)'}`,
                    borderRadius: '8px',
                    padding: '12px',
                });
                const playflowLegend = document.createElement('legend');
                playflowLegend.textContent = '재생 순서';
                Object.assign(playflowLegend.style, {
                    padding: '0 8px',
                    fontSize: '13px',
                    color: tokens.subtle || '#9da0a2',
                });
                playflowFieldset.appendChild(playflowLegend);

                const radioContainer = document.createElement('div');
                Object.assign(radioContainer.style, {
                    display: 'flex',
                    gap: '16px',
                    justifyContent: 'space-around',
                });

                const playflowOptions = {
                    allRepeat: '순서대로 반복',
                    oneRepeat: '한 곡 반복',
                    random: '랜덤 재생',
                };

                const focusable = [];
                Object.entries(playflowOptions).forEach(([value, labelText]) => {
                    const label = document.createElement('label');
                    Object.assign(label.style, {
                        display: 'inline-flex',
                        alignItems: 'center',
                        gap: '6px',
                        cursor: 'pointer',
                        fontSize: '13px',
                    });
                    const radio = document.createElement('input');
                    radio.type = 'radio';
                    radio.name = 'playflow';
                    radio.value = value;
                    if (playflow === value) radio.checked = true;

                    radio.addEventListener('change', (e) => {
                        playflow = e.target.value;
                        localStorage.setItem(STORAGE.playflow, playflow);
                    });

                    label.appendChild(radio);
                    label.appendChild(document.createTextNode(labelText));
                    radioContainer.appendChild(label);
                    focusable.push(radio);
                });
                playflowFieldset.appendChild(radioContainer);
                content.appendChild(playflowFieldset);

                const highlightFieldset = document.createElement('fieldset');
                Object.assign(highlightFieldset.style, { border: `1px solid ${tokens.border || 'rgba(255,255,255,0.18)'}`, borderRadius: '8px', padding: '12px' });
                const highlightLegend = document.createElement('legend');
                highlightLegend.textContent = 'UI';
                Object.assign(highlightLegend.style, { padding: '0 8px', fontSize: '13px', color: tokens.subtle || '#9da0a2' });
                highlightFieldset.appendChild(highlightLegend);
                const highlightOption = document.createElement('label');
                Object.assign(highlightOption.style, { display: 'flex', alignItems: 'center', justifyContent: 'space-between', cursor: 'pointer', fontSize: '13px' });
                const highlightLabel = document.createElement('span');
                highlightLabel.textContent = '현재 재생 트랙 하이라이트';
                const highlightSwitch = document.createElement('input');
                highlightSwitch.role = 'switch';
                highlightSwitch.type = 'checkbox';
                highlightSwitch.name = 'highlightToggle';
                highlightSwitch.checked = highlightActive;
                highlightSwitch.addEventListener('change', () => {
                    highlightActive = highlightSwitch.checked;
                    localStorage.setItem(STORAGE.highlight, highlightActive);
                    updatePlaylistHighlightsAndProgress(); // Apply change immediately
                });

                highlightOption.appendChild(highlightLabel);
                highlightOption.appendChild(highlightSwitch);
                highlightFieldset.appendChild(highlightOption);
                content.appendChild(highlightFieldset);
                focusable.push(highlightSwitch);

                const styleSelect = createProgressStyleSelectorWithPreview(content, tokens, isDark);
                focusable.push(styleSelect);

                // Equalizer 설정
                const eqFieldset = document.createElement('fieldset');
                Object.assign(eqFieldset.style, {
                    border: `1px solid ${tokens.border || 'rgba(255,255,255,0.18)'}`,
                    borderRadius: '8px',
                    padding: '12px',
                    marginTop: '16px',
                    overflowX: 'auto',
                    maxWidth: '550px',
                });

                const eqLegend = document.createElement('legend');
                eqLegend.textContent = '30-Band Equalizer';
                Object.assign(eqLegend.style, {
                    padding: '0 8px',
                    fontSize: '13px',
                    color: tokens.subtle | '#9da0a2',
                });

                const eqHeader = document.createElement('div');
                Object.assign(eqHeader.style, {
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    marginBottom: '12px'
                });
                eqHeader.appendChild(eqLegend);

                const eqResetBtn = mkBtn('Reset', false);
                eqResetBtn.style.padding = '4px 8px';
                eqResetBtn.style.fontSize = '11px';
                eqResetBtn.addEventListener('click',  () => {
                    const sliders = eqContainer.querySelectorAll('input[type="range"]');
                    sliders.forEach((slider, index) => {
                        slider.value = 0;
                        if (eqFilters[index]) {
                            eqFilters[index].gain.value = 0;
                        }
                    });
                    localStorage.removeItem(STORAGE.eqGains);
                });
                eqHeader.appendChild(eqResetBtn);
                eqFieldset.appendChild(eqHeader);

                const eqContainer = document.createElement('div');
                Object.assign(eqContainer.style, {
                    display: 'flex',
                    gap: '4px',
                    justifyContent: 'center',
                    padding: '10px 0',
                });

                const eqStyleId = 'custom-eq-styles';
                if (!document.getElementById(eqStyleId)) {
                    const style = document.createElement('style');
                    style.id = eqStyleId;
                    style.textContent = `
                        .eq-band { display: flex; flex-direction: column; align-items: center; width: 22px; }
                        .eq-band label { font-size: 10px; margin-top: 4px; color: ${tokens.subtle}; writing-mode: vertical-rl; text-orientation: mixed; height: 35px; }
                        .eq-band input[type="range"] {
                            writing-mode: bt-lr;
                            -webkit-appearance: slider-vertical;
                            width: 8px;
                            height: 100px;
                            padding: 0 5px;
                            cursor: pointer;
                        }
                    `;
                    document.head.appendChild(style);
                }

                if (isAudioGraphSetup) {
                    eqFilters.forEach((filter, index) => {
                        const freq = filter.frequency.value;
                        const bandDiv = document.createElement('div');
                        bandDiv.className = 'eq-band';

                        const slider = document.createElement('input');
                        slider.type = 'range';
                        slider.min = -12;
                        slider.max = 12;
                        slider.step = 0.1;
                        slider.value = filter.gain.value;
                        slider.title = `${freq < 1000 ? freq : (freq / 1000).toFixed(1) + 'k'} Hz`;

                        slider.addEventListener('input', (event) => {
                            const newValue = parseFloat(event.target.value);
                            filter.gain.value = newValue;

                            const allGains = eqFilters.map(f => f.gain.value);
                            localStorage.setItem(STORAGE.eqGains, JSON.stringify(allGains));
                        });

                        const label = document.createElement('label');
                        label.textContent = `${freq < 1000 ? freq : (freq / 1000) + 'k'}`;

                        bandDiv.appendChild(slider);
                        bandDiv.appendChild(label);
                        eqContainer.appendChild(bandDiv);
                    });
                } else {
                    eqContainer.textContent = 'Audio not started. Play a track to enable the equalizer.';
                    eqContainer.style.fontSize = '12px';
                    eqContainer.style.color = tokens.subtle;
                }

                eqFieldset.appendChild(eqContainer);
                content.appendChild(eqFieldset);
                
                const footer = document.createElement('div');
                Object.assign(footer.style, {
                    display: 'flex',
                    justifyContent: 'flex-end',
                    marginTop: '20px',
                });

                const btnClose = document.createElement('button');
                btnClose.type = 'button';
                btnClose.textContent = '닫기';
                Object.assign(btnClose.style, {
                    fontSize: '13px',
                    padding: '8px 12px',
                    borderRadius: '8px',
                    border: 'none',
                    cursor: 'pointer',
                    background: tokens.btnBg || '#0e639c',
                    color: tokens.btnFg || '#ffffff',
                });
                focusable.push(btnClose);
                footer.appendChild(btnClose);

                modal.appendChild(h);
                modal.appendChild(content);
                modal.appendChild(footer);
                overlay.appendChild(modal);
                document.body.appendChild(overlay);

                let lastFocused = document.activeElement;
                focusable[0].focus();
                const close = () => {
                    overlay.remove();
                    if (lastFocused && typeof lastFocused.focus === 'function') lastFocused.focus();
                };

                btnClose.addEventListener('click', close);
                overlay.addEventListener('click', (e) => { if (e.target === overlay) close(); });
                overlay.addEventListener('keydown', (e) => {
                    if (e.key === 'Escape') close();
                    if (e.key === 'Tab') {
                        const idx = focusable.indexOf(document.activeElement);
                        if (e.shiftKey) {
                            if (idx <= 0) {
                                focusable[focusable.length - 1].focus();
                                e.preventDefault();
                            }
                        } else {
                            if (idx === focusable.length - 1) {
                                focusable[0].focus();
                                e.preventDefault();
                            }
                        }
                    }
                });

            }

            const collapsible = document.createElement('div');
            collapsible.className = 'emp-collapsible';

            collapsible.appendChild(title);
            collapsible.appendChild(vol);
            collapsible.appendChild(speedContainer);
            collapsible.appendChild(pitchBtn);
            collapsible.appendChild(addBtn);
            collapsible.appendChild(listBtn);

            const standardControls = document.createElement('div');
            standardControls.id = 'standard-controls';
            Object.assign(standardControls.style, {
                display: 'inline-flex',
                alignItems: 'center',
                gap: '8px',
            });

            // 순서: 이전/재생/다음/제목/볼륨/속도/피치/파일/폴더/목록/접기
            standardControls.appendChild(prevBtn);
            standardControls.appendChild(playBtn);
            standardControls.appendChild(nextBtn);
            standardControls.appendChild(collapsible);
            standardControls.appendChild(settingsBtn);
            standardControls.appendChild(addBtn);
            //row.appendChild(addDirBtn);
            standardControls.appendChild(listBtn);
            standardControls.appendChild(collapseBtn);
            row.appendChild(standardControls);
            requestAnimationFrame(() => {
                collapsible.style.setProperty('--emp-open-w', collapsible.scrollWidth + 'px');
            });

            window.addEventListener('resize', () => {
                if (!collapsed) {
                    collapsible.style.setProperty('--emp-open-w', collapsible.scrollWidth + 'px');
                }
            });

            panel.appendChild(row);
            wrap.appendChild(panel);
            //wrap.appendChild(audio);
            root.appendChild(wrap);

            // Playlist panel
            const listWrap = document.createElement('div');
            listWrap.id = 'editor-music-player-list-wrapper';
            Object.assign(listWrap.style, {
                position: useFixed ? 'fixed' : 'absolute',
                top: useFixed ? '58px' : 'calc(100% + 8px)',
                right: '0',
                minWidth: '320px',
                maxWidth: '420px',
                maxHeight: '100px',
                overflowY: 'auto',
                overflowX: 'hidden',
                padding: '6px',
                display: 'none',
                zIndex: '31',
            });
            applyLiquidGlass(listWrap, tokens);

            const listHeader = document.createElement('div');
            listHeader.textContent = '재생목록';
            Object.assign(listHeader.style, { fontSize: '12px', opacity: '0.8', margin: '2px 4px 6px' });

            const list = document.createElement('div');
            list.id = 'editor-music-player-list';
            listWrap.appendChild(listHeader);
            listWrap.appendChild(list);
            wrap.appendChild(listWrap);

            // Hidden inputs
            const filePicker = document.createElement('input');
            filePicker.type = 'file'; filePicker.accept = 'audio/*'; filePicker.multiple = true; filePicker.style.display = 'none';

            const dirPicker = document.createElement('input');
            dirPicker.type = 'file'; dirPicker.multiple = true; dirPicker.style.display = 'none';
            dirPicker.setAttribute('webkitdirectory', '');

            wrap.appendChild(filePicker);
            wrap.appendChild(dirPicker);

            // State
            let playlist = [...REMOTE_PRESETS];
            let idx = clampIndex(parseInt(localStorage.getItem(STORAGE.idx) || '0', 10));
            let collapsed = localStorage.getItem(STORAGE.collapsed) === 'true';
            let listOpen = localStorage.getItem(STORAGE.listOpen) === 'true';
            let playflow = localStorage.getItem(STORAGE.playflow) || 'allRepeat';
            let highlightActive = JSON.parse(localStorage.getItem(STORAGE.highlight) ?? 'true');
            const localObjectURLs = new Set();

            // Utils
            function clampIndex(i) {
                if (!playlist.length) return 0;
                return (i % playlist.length + playlist.length) % playlist.length;
            }
            function updatePlayIcon() {
                playBtn.textContent = audio.paused ? '▶' : '⏸';
            }
            function setCollapsed(v) {
                collapsed = !!v;
                localStorage.setItem(STORAGE.collapsed, String(collapsed));
                if (!collapsed) {
                    requestAnimationFrame(() => {
                        collapsible.style.setProperty('--emp-open-w', collapsible.scrollWidth + 'px');
                    });
                }

                wrap.classList.toggle('is-collapsed', collapsed);
                collapsible.setAttribute('aria-hidden', String(collapsed));

                try { collapsible.inert = collapsed; } catch { }

                collapseBtn.textContent = collapsed ? '▣' : '–';
                collapseBtn.title = collapsed ? '펼치기' : '접기';
                if (collapsed) hideList();
            }
            function setListOpen(v) {
                listOpen = !!v;
                localStorage.setItem(STORAGE.listOpen, String(listOpen));
                listWrap.style.display = listOpen ? 'block' : 'none';
            }
            function toggleList() { setListOpen(!listOpen); }
            function hideList() { setListOpen(false); }

            function humanTitleFromFile(file) {
                return file.name.replace(/\.[^.]+$/, '');
            }
            function isAudioFile(file) {
                if (file.type && file.type.startsWith('audio/')) return true;
                return /\.(mp3|m4a|aac|flac|wav|ogg|oga|opus|webm)$/i.test(file.name);
            }
            function genId() {
                return 't_' + Date.now().toString(36) + '_' + Math.random().toString(36).slice(2, 8);
            }

            function addFiles(filesLike) {
                const files = Array.from(filesLike || []).filter(isAudioFile);
                if (!files.length) return;
                const wasEmpty = playlist.length === 0;
                files.forEach((f) => {
                    const url = URL.createObjectURL(f);
                    localObjectURLs.add(url);
                    playlist.push({ id: genId(), title: humanTitleFromFile(f), url, file: f, source: 'local' });
                });
                renderPlaylist();
                if (wasEmpty) setCurrent(0, false);
            }

            function removeTrackById(id) {
                const removeIdx = playlist.findIndex((t) => t.id === id);
                if (removeIdx === -1) return;

                const wasCurrent = removeIdx === idx;
                const t = playlist[removeIdx];

                if (t.source === 'local' && t.url && localObjectURLs.has(t.url)) {
                    try { URL.revokeObjectURL(t.url); } catch { }
                    localObjectURLs.delete(t.url);
                }
                playlist.splice(removeIdx, 1);
                if (!playlist.length) {
                    setCurrent(0, false);
                } else {
                    if (removeIdx < idx) idx -= 1;
                    else if (wasCurrent) idx = clampIndex(idx);
                    setCurrent(idx, false);
                }
                renderPlaylist();
            }

            function setCurrent(i, autoplay) {
                if (!playlist.length) {
                    const playerContainer = document.getElementById('player-container');
                    const standardControls = document.getElementById('standard-controls');
                    if (playerContainer) playerContainer.innerHTML = '';
                    if (standardControls) standardControls.style.display = 'inline-flex';

                    audio.pause();
                    audio.src = '';
                    title.textContent = '재생목록이 비었습니다.';
                    updatePlayIcon();
                    return;
                }
                idx = clampIndex(i);
                localStorage.setItem(STORAGE.idx, String(idx));
                const t = playlist[idx];

                const playerContainer = document.getElementById('player-container');
                const standardControls = document.getElementById('standard-controls');

                if (!playerContainer || !standardControls) {
                    console.error("Player UI (player-container or standard-controls) not found!");
                    return;
                }

                playerContainer.innerHTML = '';

                title.textContent = t.title || 'Untitled';
                updatePlaylistHighlightsAndProgress();

                if (t.source === 'soundcloud') {
                    standardControls.style.display = 'none';
                    audio.pause();
                    audio.src = '';
                    let iframeHtml = t.iframe;
                    if (autoplay) {
                        const separator = iframeHtml.includes('?') ? '&' : '?';
                        iframeHtml = iframeHtml.replace('src="', `src="${separator}auto_play=true`);
                    }
                    playerContainer.innerHTML = iframeHtml;
                    playBtn.textContent = '⏸';
                } else {
                    standardControls.style.display = 'inline-flex';
                    playerContainer.appendChild(audio);
                    
                    let sourceUrl = t.url;
                    if (t.source === 'local' && t.file && !t.url) {
                        sourceUrl = URL.createObjectURL(t.file);
                        t.url = sourceUrl;
                        localObjectURLs.add(sourceUrl);
                    }

                    audio.src = sourceUrl || '';
                    //applyRateFromUI();
                    updateSpeedUIAndAudio();
                    setPitchPreserve(pitchPreserve);

                    if (autoplay) {
                        const playPromise = audio.play();
                        if (playPromise !== undefined) {
                            playPromise.catch((e) => {
                                console.error("오디오 재생 실패:", e);
                                updatePlayIcon();
                            });
                        }
                    } else {
                        updatePlayIcon();
                    }
                }
            }
            function nextTrack(autoplay) { if (playlist.length) setCurrent(idx + 1, autoplay ?? !audio.paused); }
            function prevTrack() { if (playlist.length) setCurrent(idx - 1, !audio.paused); }
            function randomNextTrack(autoplay) {
                if (playlist.length <= 1) {
                    if (playlist.length === 1) setCurrent(0, autoplay);
                    return;
                }
                let newIndex;
                do {
                    newIndex = Math.floor(Math.random() * playlist.length);
                } while (newIndex === idx);
                setCurrent(newIndex, autoplay);
            }

            function updatePlaylistHighlightsAndProgress() {
                const rows = list.querySelectorAll('[data-track-id]');

                if (!highlightActive) {
                    rows.forEach(row => {
                        row.style.backgroundColor = 'transparent';
                        row.style.backgroundImage = 'none';
                        row.classNAme = row.className.replace(/progress-\w+/g, '');
                    });
                    return;
                }
                const currentTrackId = playlist[idx]?.id;
                let progress = 0;
                if (audio.duration > 0) {
                    progress = (audio.currentTime / audio.duration) * 100;
                }

                const highlightColor = tokens.hoverBg || (isDark ? '#2a2a2a' : '#f0f0f0');
                const progressFillColor = tokens.btnBg ? `color-mix(in oklab, ${tokens.btnBg} 40%, transparent)` : (isDark ? 'rgba(58, 122, 181, 0.4)' : 'rgba(14, 99, 156, 0.3)');

                rows.forEach((row) => {
                    const isActive = row.getAttribute('data-track-id') === currentTrackId;
                    row.className = row.className.replace(/progress-\w+/g, '');
                    if (isActive) {
                        row.style.backgroundColor = highlightColor;
                        row.style.setProperty('--progress-percent', `${progress}%`);
                        row.style.setProperty('--progress-color', progressFillColor);

                        row.classList.add(`progress-${currentProgressStyle}`);
                    } else {
                        row.style.backgroundColor = 'transparent';
                        row.style.backgroundImage = 'none';
                        row.style.removeProperty('--progress-percent');
                        row.style.removeProperty('--progress-color');
                    }
                });
            }

            function renderPlaylist() {
                list.innerHTML = '';
                playlist.forEach((t, i) => {
                    const row = document.createElement('div');
                    row.setAttribute('data-track-id', t.id);
                    Object.assign(row.style, {
                        display: 'grid',
                        gridTemplateColumns: 'auto 1fr auto',
                        alignItems: 'center',
                        gap: '8px',
                        padding: '6px',
                        borderRadius: '8px',
                        cursor: 'pointer',
                    });

                    const indexBadge = document.createElement('span');
                    indexBadge.textContent = String(i + 1).padStart(2, '0');
                    Object.assign(indexBadge.style, { fontSize: '11px', opacity: '0.7', width: '24px', textAlign: 'right' });

                    const name = document.createElement('div');
                    name.textContent = t.title || 'Untitled';
                    Object.assign(name.style, { fontSize: '12px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' });

                    const del = document.createElement('button');
                    del.type = 'button';
                    del.textContent = '✕';
                    del.title = '이 트랙 제거';
                    Object.assign(del.style, {
                        fontSize: '12px',
                        padding: '2px 6px',
                        borderRadius: '6px',
                        border: '1px solid ' + (tokens.border || 'rgba(255,255,255,0.18)'),
                        background: 'transparent',
                        color: tokens.fg || '#e6e6e6',
                        cursor: 'pointer',
                    });
                    del.onmouseenter = () => { del.style.background = tokens.hoverBg || (isDark ? '#2a2a2a' : '#f3f3f3'); };
                    del.onmouseleave = () => { del.style.background = 'transparent'; };

                    del.addEventListener('click', (e) => { e.stopPropagation(); removeTrackById(t.id); });
                    row.addEventListener('click', () => {
                        const goIndex = playlist.findIndex((x) => x.id === t.id);
                        if (goIndex !== -1) setCurrent(goIndex, true);
                    });

                    row.appendChild(indexBadge);
                    row.appendChild(name);
                    row.appendChild(del);
                    list.appendChild(row);
                });
                updatePlaylistHighlightsAndProgress();
            }

            // Init
            audio.volume = parseFloat(vol.value);
            //applyRateFromUI();
            updateSpeedUIAndAudio();
            setPitchPreserve(pitchPreserve);
            setCollapsed(localStorage.getItem(STORAGE.collapsed) === 'true');
            setListOpen(localStorage.getItem(STORAGE.listOpen) === 'true');
            if (playlist.length) setCurrent(idx, false);
            renderPlaylist();
            updatePlayIcon();

            // Events
            playBtn.addEventListener('click', () => {
                if (audioContext && audioContext.state === 'suspended') {
                    audioContext.resume();
                }
                if (!playlist.length) return filePicker.click();
                if (audio.paused) audio.play().catch(() => { }); else audio.pause();
            });
            nextBtn.addEventListener('click', () => nextTrack(true));
            prevBtn.addEventListener('click', () => prevTrack());
            collapseBtn.addEventListener('click', () => setCollapsed(!collapsed));
            listBtn.addEventListener('click', () => toggleList());

            vol.addEventListener('input', () => {
                audio.volume = parseFloat(vol.value);
                localStorage.setItem(STORAGE.vol, vol.value);
            });

            audio.addEventListener('play', () => { updatePlayIcon(); updatePlaylistHighlightsAndProgress(); });
            audio.addEventListener('pause', () => { updatePlayIcon(); updatePlaylistHighlightsAndProgress(); });
            audio.addEventListener('timeupdate', updatePlaylistHighlightsAndProgress);
            audio.addEventListener('ended', () => {
                switch (playflow) {
                    case 'oneRepeat':
                        audio.currentTime = 0;
                        audio.play();
                        break;
                    case 'random':
                        randomNextTrack(true);
                        break;
                    case 'allRepeat':
                    default:
                        nextTrack(true);
                        break;
                }
            });
            audio.addEventListener('error', () => {
                title.textContent = '재생 오류: 다음 트랙으로 이동';
                setTimeout(() => nextTrack(true), 800);
            });

            function showAddModal() {
                if (document.getElementById('custom-add-source-overlay')) return;
                
                const overlay = document.createElement('div');
                overlay.id = 'custom-add-source-overlay';
                overlay.setAttribute('role', 'dialog');
                overlay.setAttribute('aria-modal', 'true');
                Object.assign(overlay.style, {
                    position: 'fixed',
                    inset: '0',
                    background: tokens.bg || 'rgba(0,0,0,0.35)',
                    backdropFilter: 'blur(2px)',
                    zIndex: '10000',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                });

                const modal = document.createElement('div');
                Object.assign(modal.style, {
                    minWidth: '420px',
                    maxWidth: '560px',
                    padding: '20px 22px',
                    borderRadius: '12px',
                    background: tokens.bg || 'rgba(255,255,255,0.06)',
                    color: tokens.fg || '#e6e6e6',
                    boxShadow: '0 20px 60px rgba(0,0,0,0.25), 0 2px 12px rgba(0,0,0,0.2)',
                    border: `1px solid ${tokens.border || 'rgba(255,255,255,0.18)'}`,
                    fontFamily: 'var(--vscode-font-family, system-ui, -apple-system, Segoe UI, Roboto, sans-serif)',
                });
                applyLiquidGlass(modal, tokens);
                const h = document.createElement('div');
                h.textContent = '항목 추가';
                Object.assign(h.style, { fontSize: '18px', fontWeight: '600', marginBottom: '12px' });

                const desc = document.createElement('div');
                desc.textContent = '로컬 파일/폴더를 추가할 수 있습니다.';
                Object.assign(desc.style, { fontSize: '12px', color: tokens.subtle || '#9da0a2', marginBottom: '12px' });

                const actions = document.createElement('div');
                Object.assign(actions.style, {
                    display: 'grid',
                    gridTemplateColumns: '1fr 1fr',
                    gap: '10px',
                    marginBottom: '12px',
                });

                const btnFile = mkBtn('파일 선택...', '파일 선택');
                const btnFolder = mkBtn('폴더 선택...', '폴더 선택');
                actions.appendChild(btnFile);
                actions.appendChild(btnFolder);

                btnFile.addEventListener('click', () => {
                    close();
                    filePicker.click();
                });
                btnFolder.addEventListener('click', () => {
                    close();
                    dirPicker.click();
                });

                const footer = document.createElement('div');
                Object.assign(footer.style, { display: 'flex', justifyContent: 'flex-end', marginTop: '16px', gap: '8px' });
                const btnClose = mkBtn('닫기', '닫기');
                btnClose.addEventListener('click', () => {
                    close();
                });
                footer.appendChild(btnClose);

                modal.appendChild(h);
                modal.appendChild(desc);
                modal.appendChild(actions);
                modal.appendChild(footer);
                overlay.appendChild(modal);
                document.body.appendChild(overlay);

                const focusable = [btnFile, btnFolder, btnClose];
                setTimeout(() => urlInput.focus(), 0);

                function close() {
                    overlay.remove();
                }
                btnClose.addEventListener('click', close);
                overlay.addEventListener('click', (e) => { if (e.target === overlay) close(); });
                overlay.addEventListener('keydown', (e) => {
                    if (e.key === 'Escape') return close();
                    if (e.key === 'Tab') {
                        const idx = focusable.indexOf(document.activeElement);
                        if (e.shiftKey) {
                            if (idx <= 0) {
                                focusable[focusable.length - 1].focus();
                                e.preventDefault();
                            }
                        } else {
                            if (idx === focusable.length - 1) {
                                focusable[0].focus();
                                e.preventDefault();
                            }
                        }
                    }
                });
            }

            // 프로그래스 스타일 설정

            const PROGRESS_STYLES = {
                linear: 'Linear Fill',
                gradient: 'Gradient Wave',
                pulse: 'Pulse Beat',
                wave: 'Wave Motion',
                glow: 'Glow Effect',
                particles: 'Particle Flow',
                spectrum: 'Spectrum Bars',
                liquid: 'Liquid Flow'
            };

            let currentProgressStyle = localStorage.getItem('vscodeAudioPlayer.progressStyle') || 'linear';

            function injectProgressStyles(tokens, isDark) {
                const styleId = 'enhanced-select-styles';
                let style = document.getElementById(styleId);
                
                if (!style) {
                    style = document.createElement('style');
                    style.id = styleId;
                    document.head.appendChild(style);
                }

                const accent = tokens.btnBg || '#0e639c';
                const fg = tokens.fg || (isDark ? '#e6e6e6' : '#1e1e1e');
                const bg = tokens.bg || (isDark ? '#252526' : '#ffffff');
                const border = tokens.border || (isDark ? '#3c3c3c' : '#e5e5e5');
                const hover = tokens.hoverBg || (isDark ? '#2a2a2a' : '#f3f3f3');
                const subtle = tokens.subtle || (isDark ? '#9da0a2' : '#6b6f73');

                style.textContent = `
                    /* Linear Fill (기본) */
                    .progress-linear {
                        background-image: linear-gradient(to right, var(--progress-color) var(--progress-percent), transparent var(--progress-percent)) !important;
                    }
                    
                    /* Gradient Wave */
                    .progress-gradient {
                        background: linear-gradient(45deg, 
                            transparent 0%, 
                            var(--progress-color) var(--progress-percent), 
                            transparent calc(var(--progress-percent) + 5%)) !important;
                        background-size: 200% 100%;
                        animation: gradientShift 3s ease-in-out infinite;
                    }
                    
                    /* Pulse Beat */
                    .progress-pulse {
                        background: linear-gradient(to right, var(--progress-color) var(--progress-percent), transparent var(--progress-percent)) !important;
                        animation: pulseGlow 0.8s ease-in-out infinite alternate;
                    }
                    
                    /* Wave Motion */
                    .progress-wave {
                        background: 
                            linear-gradient(to right, var(--progress-color) var(--progress-percent), transparent var(--progress-percent)),
                            repeating-linear-gradient(90deg, 
                                transparent 0px, 
                                rgba(255,255,255,0.1) 2px, 
                                transparent 4px, 
                                transparent 8px);
                        animation: waveMove 2s linear infinite;
                    }
                    
                    /* Glow Effect */
                    .progress-glow {
                        background: linear-gradient(to right, var(--progress-color) var(--progress-percent), transparent var(--progress-percent)) !important;
                        position: relative;
                    }
                    .progress-glow::before {
                        content: '';
                        position: absolute;
                        top: 0; left: 0; right: 0; bottom: 0;
                        background: radial-gradient(ellipse at var(--progress-percent) center, 
                            rgba(255,255,255,0.3) 0%, 
                            transparent 70%);
                        animation: glowPulse 1.5s ease-in-out infinite;
                        pointer-events: none;
                    }
                    
                    /* Particle Flow */
                    .progress-particles {
                        background: linear-gradient(to right, var(--progress-color) var(--progress-percent), transparent var(--progress-percent)) !important;
                        position: relative;
                        overflow: hidden;
                    }
                    .progress-particles::after {
                        content: '';
                        position: absolute;
                        width: 100%; height: 100%;
                        background: 
                            radial-gradient(circle at 20% 50%, rgba(255,255,255,0.4) 1px, transparent 1px),
                            radial-gradient(circle at 60% 30%, rgba(255,255,255,0.3) 1px, transparent 1px),
                            radial-gradient(circle at 80% 70%, rgba(255,255,255,0.4) 1px, transparent 1px);
                        background-size: 40px 40px, 30px 30px, 35px 35px;
                        animation: particleFlow 4s linear infinite;
                        opacity: 0.6;
                    }
                    
                    /* Spectrum Bars */
                    .progress-spectrum {
                        background: var(--progress-color) !important;
                        position: relative;
                        overflow: hidden;
                    }
                    .progress-spectrum::before {
                        content: '';
                        position: absolute;
                        width: var(--progress-percent);
                        height: 100%;
                        background: repeating-linear-gradient(to right,
                            rgba(255,255,255,0.3) 0px,
                            rgba(255,255,255,0.3) 2px,
                            transparent 2px,
                            transparent 6px);
                        animation: spectrumBeat 0.5s ease-in-out infinite alternate;
                    }
                    
                    /* Liquid Flow */
                    .progress-liquid {
                        background: var(--progress-color) !important;
                        position: relative;
                        overflow: hidden;
                    }
                    .progress-liquid::after {
                        content: '';
                        position: absolute;
                        top: -50%; left: 0;
                        width: var(--progress-percent);
                        height: 200%;
                        background: linear-gradient(45deg, 
                            transparent 30%, 
                            rgba(255,255,255,0.2) 50%, 
                            transparent 70%);
                        animation: liquidWave 3s ease-in-out infinite;
                        transform: skewX(-20deg);
                    }
                    
                    /* 애니메이션 키프레임들 */
                    @keyframes gradientShift {
                        0% { background-position: 0% center; }
                        50% { background-position: 100% center; }
                        100% { background-position: 0% center; }
                    }
                    
                    @keyframes pulseGlow {
                        0% { filter: brightness(1) drop-shadow(0 0 2px var(--progress-color)); }
                        100% { filter: brightness(1.3) drop-shadow(0 0 8px var(--progress-color)); }
                    }
                    
                    @keyframes waveMove {
                        0% { background-position: 0 0, 0 0; }
                        100% { background-position: 0 0, 20px 0; }
                    }
                    
                    @keyframes glowPulse {
                        0%, 100% { opacity: 0.3; transform: scale(0.8); }
                        50% { opacity: 0.8; transform: scale(1.2); }
                    }
                    
                    @keyframes particleFlow {
                        0% { transform: translateX(-100%); }
                        100% { transform: translateX(100%); }
                    }
                    
                    @keyframes spectrumBeat {
                        0% { transform: scaleY(0.8); }
                        100% { transform: scaleY(1.2); }
                    }
                    
                    @keyframes liquidWave {
                        0%, 100% { transform: skewX(-20deg) translateY(0%); }
                        50% { transform: skewX(-20deg) translateY(-10%); }
                    }
                    
                    /* 접근성 고려 */
                    @media (prefers-reduced-motion: reduce) {
                        .progress-gradient, .progress-pulse, .progress-wave, 
                        .progress-glow::before, .progress-particles::after,
                        .progress-spectrum::before, .progress-liquid::after {
                            animation: none !important;
                        }
                    }

                    /* Enhanced Select with Liquid Glass Effect */
                    .lg-select {
                        position: relative;
                        display: inline-block;
                        width: 100%;
                    }
                    
                    .lg-select select {
                        -webkit-appearance: none;
                        -moz-appearance: none;
                        appearance: none;
                        width: 100%;
                        padding: 12px 40px 12px 16px;
                        font-size: 13px;
                        line-height: 1.4;
                        color: ${fg};
                        background: linear-gradient(145deg, 
                            color-mix(in oklab, ${bg} 85%, white 15%) 0%,
                            color-mix(in oklab, ${bg} 92%, transparent 8%) 100%);
                        border: 1px solid color-mix(in oklab, ${border} 70%, transparent 30%);
                        border-radius: 10px;
                        cursor: pointer;
                        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                        backdrop-filter: blur(8px);
                        box-shadow: 
                            inset 0 1px 0 rgba(255,255,255,0.1),
                            inset 0 -1px 0 rgba(0,0,0,0.05),
                            0 2px 8px rgba(0,0,0,0.1);
                        position: relative;
                        z-index: 1;
                    }
                    
                    .lg-select select:hover {
                        background: linear-gradient(145deg, 
                            color-mix(in oklab, ${hover} 90%, white 10%) 0%,
                            color-mix(in oklab, ${hover} 95%, transparent 5%) 100%);
                        border-color: color-mix(in oklab, ${accent} 60%, transparent 40%);
                        box-shadow: 
                            inset 0 1px 0 rgba(255,255,255,0.15),
                            inset 0 -1px 0 rgba(0,0,0,0.05),
                            0 4px 12px rgba(0,0,0,0.15),
                            0 0 0 1px color-mix(in oklab, ${accent} 30%, transparent 70%);
                    }
                    
                    .lg-select select:focus {
                        outline: none;
                        border-color: ${accent};
                        box-shadow: 
                            inset 0 1px 0 rgba(255,255,255,0.2),
                            inset 0 -1px 0 rgba(0,0,0,0.05),
                            0 0 0 3px color-mix(in oklab, ${accent} 25%, transparent 75%),
                            0 8px 20px rgba(0,0,0,0.2);
                    }
                    
                    .lg-select select:active {
                        transform: translateY(0px);
                        box-shadow: 
                            inset 0 2px 4px rgba(0,0,0,0.1),
                            0 2px 4px rgba(0,0,0,0.1);
                    }
                    
                    /* Custom Dropdown Arrow with Liquid Glass Effect */
                    .lg-select::after {
                        content: '';
                        position: absolute;
                        top: 50%;
                        right: 12px;
                        width: 0;
                        height: 0;
                        border-left: 6px solid transparent;
                        border-right: 6px solid transparent;
                        border-top: 6px solid ${fg};
                        opacity: 0.7;
                        transition: all 0.3s ease;
                        pointer-events: none;
                        z-index: 2;
                    }
                    
                    /* Animated Background Shimmer */
                    .lg-select::before {
                        content: '';
                        position: absolute;
                        top: 0; left: 0; right: 0; bottom: 0;
                        background: linear-gradient(45deg, 
                            transparent 30%, 
                            rgba(255,255,255,0.1) 50%, 
                            transparent 70%);
                        background-size: 200% 200%;
                        border-radius: 10px;
                        opacity: 0;
                        transition: opacity 0.3s ease;
                        pointer-events: none;
                        z-index: 0;
                    }
                    
                    .lg-select:hover::before {
                        opacity: 1;
                    }
                    
                    /* Option Styling (for supported browsers) */
                    .lg-select select option {
                        background: ${bg};
                        color: ${fg};
                        padding: 8px 12px;
                        border: none;
                    }
                    
                    .lg-select select option:hover {
                        background: ${hover};
                    }
                    
                    .lg-select select option:checked {
                        background: color-mix(in oklab, ${accent} 20%, ${bg} 80%);
                        color: ${fg};
                    }
                    
                    /* Preview Styles */
                    .style-preview {
                        display: flex;
                        align-items: center;
                        gap: 12px;
                        padding: 8px 12px;
                        margin-top: 8px;
                        border-radius: 8px;
                        background: color-mix(in oklab, ${bg} 95%, transparent 5%);
                        border: 1px solid color-mix(in oklab, ${border} 50%, transparent 50%);
                    }
                    
                    .style-preview-bar {
                        flex: 1;
                        height: 6px;
                        border-radius: 3px;
                        background: color-mix(in oklab, ${border} 30%, transparent 70%);
                        position: relative;
                        overflow: hidden;
                    }
                    
                    .style-preview-progress {
                        height: 100%;
                        width: 65%;
                        background: ${accent};
                        border-radius: 3px;
                        transition: all 0.3s ease;
                    }
                    
                    .style-preview-label {
                        font-size: 11px;
                        color: ${subtle};
                        min-width: 60px;
                        text-align: right;
                    }
                    
                    /* Animation Keyframes */
                    @keyframes selectShimmer {
                        0% { background-position: -200% -200%; }
                        50% { background-position: 200% 200%; }
                        100% { background-position: -200% -200%; }
                    }
                    
                    /* Dark/Light Theme Adjustments */
                    ${isDark ? `
                        .lg-select select {
                            background: linear-gradient(145deg, 
                                rgba(255,255,255,0.05) 0%,
                                rgba(255,255,255,0.02) 100%);
                        }
                        .lg-select select:hover {
                            background: linear-gradient(145deg, 
                                rgba(255,255,255,0.08) 0%,
                                rgba(255,255,255,0.04) 100%);
                        }
                    ` : `
                        .lg-select select {
                            background: linear-gradient(145deg, 
                                rgba(255,255,255,0.9) 0%,
                                rgba(255,255,255,0.7) 100%);
                        }
                        .lg-select select:hover {
                            background: linear-gradient(145deg, 
                                rgba(255,255,255,0.95) 0%,
                                rgba(255,255,255,0.85) 100%);
                        }
                    `}
                    
                    /* Reduce Motion Support */
                    @media (prefers-reduced-motion: reduce) {
                        .lg-select select,
                        .lg-select::after,
                        .lg-select::before,
                        .style-preview-progress {
                            transition: none !important;
                            animation: none !important;
                        }
                    }
                    
                    /* Mobile Responsive */
                    @media (max-width: 480px) {
                        .lg-select select {
                            padding: 10px 36px 10px 14px;
                            font-size: 14px;
                        }
                    }
                `;
            }
            
            function createLiquidGlassSelect(options, selectedValue, changeCallback) {
                const wrapper = document.createElement('div');
                wrapper.className = 'lg-select';
                
                const select = document.createElement('select');
                
                // Add options
                Object.entries(options).forEach(([value, label]) => {
                    const option = document.createElement('option');
                    option.value = value;
                    option.textContent = label;
                    if (value === selectedValue) option.selected = true;
                    select.appendChild(option);
                });
                
                // Add change event
                select.addEventListener('change', changeCallback);
                
                wrapper.appendChild(select);
                return { wrapper, select };
            }

            // Enhanced style selector with preview
            function createProgressStyleSelectorWithPreview(content, tokens, isDark) {
                const container = document.createElement('div');
                
                const label = document.createElement('label');
                label.textContent = '진행률 표시 스타일';
                Object.assign(label.style, {
                    display: 'block',
                    fontSize: '13px',
                    fontWeight: '500',
                    color: tokens.fg || '#e6e6e6',
                    marginBottom: '8px'
                });
                
                const { wrapper: selectWrapper, select } = createLiquidGlassSelect(
                    PROGRESS_STYLES, 
                    currentProgressStyle, 
                    (e) => {
                        currentProgressStyle = e.target.value;
                        localStorage.setItem('vscodeAudioPlayer.progressStyle', currentProgressStyle);
                        updatePreview(e.target.value);
                        updatePlaylistHighlightsAndProgress(); // 즉시 적용
                    }
                );
                
                // Preview container
                const preview = document.createElement('div');
                preview.className = 'style-preview';
                
                const previewBar = document.createElement('div');
                previewBar.className = 'style-preview-bar';
                
                const previewProgress = document.createElement('div');
                previewProgress.className = 'style-preview-progress';
                
                const previewLabel = document.createElement('div');
                previewLabel.className = 'style-preview-label';
                previewLabel.textContent = 'Preview';
                
                previewBar.appendChild(previewProgress);
                preview.appendChild(previewBar);
                preview.appendChild(previewLabel);
                
                // Update preview function
                function updatePreview(style) {
                    // Reset classes
                    previewProgress.className = 'style-preview-progress';
                    
                    // Add style class
                    previewProgress.classList.add(`progress-${style}`);
                    
                    // Set CSS variables for preview
                    previewProgress.style.setProperty('--progress-percent', '65%');
                    previewProgress.style.setProperty('--progress-color', tokens.btnBg || '#0e639c');
                    
                    // Update label
                    previewLabel.textContent = PROGRESS_STYLES[style];
                }
                
                container.appendChild(label);
                container.appendChild(selectWrapper);
                container.appendChild(preview);
                
                // Initialize preview
                updatePreview(currentProgressStyle);
                
                content.appendChild(container);
                return select;
            }

            // 파일/폴더 추가
            addBtn.addEventListener('click', showAddModal);
            filePicker.addEventListener('change', () => {
                addFiles(filePicker.files);
                filePicker.value = '';
            });
            dirPicker.addEventListener('change', () => {
                addFiles(dirPicker.files);
                dirPicker.value = '';
            });

            // 드래그&드롭
            const dragHighlightOn = () => { panel.style.outline = `2px dashed ${tokens.border || (isDark ? '#3c3c3c' : '#cfcfcf')}`; };
            const dragHighlightOff = () => { panel.style.outline = 'none'; };
            panel.addEventListener('dragover', (e) => {
                if (e.dataTransfer && Array.from(e.dataTransfer.items || []).some(it => it.kind === 'file')) {
                    e.preventDefault(); e.stopPropagation(); dragHighlightOn();
                }
            });
            panel.addEventListener('dragleave', dragHighlightOff);
            panel.addEventListener('drop', (e) => {
                e.preventDefault(); e.stopPropagation(); dragHighlightOff();
                if (e.dataTransfer && e.dataTransfer.files) addFiles(e.dataTransfer.files);
            });

            // unload 시 ObjectURL 정리
            function revokeAllLocalUrls() {
                localObjectURLs.forEach((u) => { try { URL.revokeObjectURL(u); } catch { } });
                localObjectURLs.clear();
            }
            window.addEventListener('unload', revokeAllLocalUrls);
        }
    })();

    // TODO VSCode 에디터 영역 안의 오른쪽 위에 음악을 재생할 수 있는 기능 추가 (완료)
});