var LayoutCtrl = (function () {

    function LayoutCtrl() { }

    LayoutCtrl.prototype.setEvents = function () {
        setEventsBtnToggle();
        setEventsOverlayBlack();
    };

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
    }

    return LayoutCtrl;

}());

var layoutCtrl = new LayoutCtrl();
layoutCtrl.setEvents();