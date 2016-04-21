function LayoutCtrl() {

    var isSidebarShow = false;

    function setEvents() {
        setEventsBtnToggle();
        setEventsOverlayBlack();
    }

    function setEventsBtnToggle() {
        $('.btn-toggle-right').on('click', function (event) {
            toggleMenu();
            event.preventDefault();
        });
        
        $('.btn-toggle-right').on('mousemove', function (event) {
            toggleMenu();
            event.preventDefault();
        });

        $('.btn-toggle-left').on(/*'mousemove'*/ 'click', function (event) {
            toggleMenu();
            event.preventDefault();
        });
    }

    function setEventsOverlayBlack() {
        $('.overlay').on('click', function (event) {
            toggleMenu();
            event.preventDefault();
        });
    }

    function toggleMenu() {
        $('.sidebar').toggleClass('sidebar-toggled');
        $('.overlay').toggleClass('overlay-toggled');
        $('body').toggleClass('body-toggled');
        isSidebarShow = !isSidebarShow;
    }

    // public attributes and methods:
    this.setEvents = setEvents;
}

var layoutCtrl = new LayoutCtrl();
layoutCtrl.setEvents();