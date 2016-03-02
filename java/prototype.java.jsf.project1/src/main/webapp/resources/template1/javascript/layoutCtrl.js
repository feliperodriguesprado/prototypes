var LayoutCtrl = (function () {

    function LayoutCtrl() {

        this.isSidebarShow = false;

    }

    LayoutCtrl.prototype.setEvents = function () {
        setEventsBtnToggle();
        setEventsOverlayBlack();
        setEventMouseOver();
    };

    function setEventMouseOver() {
        window.addEventListener('mousemove', function (event) {
            if (event.pageX < 10 && !LayoutCtrl.isSidebarShow) {
                toggleMenu();
                event.preventDefault();
            }
        });
    }

    function setEventsBtnToggle() {

        document.getElementById('btn-toggle').addEventListener('click', function (event) {
            toggleMenu();
            event.preventDefault();
        });
    }

    function setEventsOverlayBlack() {
        document.getElementById('overlay').addEventListener('click', function (event) {
            toggleMenu();
            event.preventDefault();
        });
    }

    function toggleMenu() {
        $('#sidebar').toggleClass('sidebar-toggled');
        $('#section').toggleClass('section-toggled');
        $('#overlay').toggleClass('overlay-toggle');

        LayoutCtrl.isSidebarShow = true;

        setTimeout(function () {
            LayoutCtrl.isSidebarShow = false;
        }, 2000);
    }

    return LayoutCtrl;

}());

var layoutCtrl = new LayoutCtrl();
layoutCtrl.setEvents();