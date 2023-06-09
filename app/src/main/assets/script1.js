addButtons();

function addButtons() {
    // setting up the rewind button by setting up a video-js component
    var Button = THEOplayer.videojs.getComponent('Button');
    var RewindButton = THEOplayer.videojs.extend(Button, {
        constructor: function() {
            Button.apply(this, arguments);
            /* initialize your button */

            // add tooltip
            var tooltipSpan = document.createElement('span');
            tooltipSpan.className = 'theo-button-tooltip vjs-hidden';
            tooltipSpan.innerText = 'Go back 10 seconds';
            function toggleTooltip() {
                tooltipSpan.classList.toggle('vjs-hidden');
            }
            this.el().addEventListener('mouseover', toggleTooltip);
            this.el().addEventListener('mouseout', toggleTooltip);
            this.el().appendChild(tooltipSpan);
        },
        handleClick: () => {
            player.currentTime -= 10;
        },
        buildCSSClass: function () {
            return 'fa fa-step-backward  vjs-button'; // insert all class names here
        }
    });

    THEOplayer.videojs.registerComponent('RewindButton', RewindButton);
    player.ui.getChild('controlBar').addChild('RewindButton', {});

    // setting up the forward button by setting up a video-js component
    var Button = THEOplayer.videojs.getComponent('Button');
    var ForwardButton = THEOplayer.videojs.extend(Button, {
        constructor: function() {
            Button.apply(this, arguments);
            /* initialize your button */

            // add tooltip
            var tooltipSpan = document.createElement('span');
            tooltipSpan.className = 'theo-button-tooltip vjs-hidden';
            tooltipSpan.innerText = 'Go forward 10 seconds';
            function toggleTooltip() {
                tooltipSpan.classList.toggle('vjs-hidden');
            }
            this.el().addEventListener('mouseover', toggleTooltip);
            this.el().addEventListener('mouseout', toggleTooltip);
            this.el().appendChild(tooltipSpan);
        },
        handleClick: () => {
            player.currentTime += 10;
        },
        buildCSSClass: function () {
            return 'fa fa-step-forward vjs-button'; // insert all class names here
        }
    });

    THEOplayer.videojs.registerComponent('ForwardButton', ForwardButton);
    player.ui.getChild('controlBar').addChild('ForwardButton', {});
}
