function IndexCtrl() {

    var data;
    var grid;
    var pager;

    function initialize() {
        data = [
            {column1: 1, column2: 'Text 1'},
            {column1: 2, column2: 'Text 2'},
            {column1: 3, column2: 'Text 3'},
            {column1: 4, column2: 'Text 4'},
            {column1: 5, column2: 'Text 5'},
            {column1: 6, column2: 'Text 6'},
            {column1: 7, column2: 'Text 7'},
            {column1: 8, column2: 'Text 8'},
            {column1: 9, column2: 'Text 9'},
            {column1: 11, column2: 'Text 11'},
            {column1: 12, column2: 'Text 12'},
            {column1: 13, column2: 'Text 13'},
            {column1: 14, column2: 'Text 14'},
            {column1: 15, column2: 'Text 15'},
            {column1: 16, column2: 'Text 16'},
            {column1: 17, column2: 'Text 17'},
            {column1: 18, column2: 'Text 18'},
            {column1: 19, column2: 'Text 19'},
            {column1: 20, column2: 'Text 20'}
        ];
        grid = $('#grid');
        pager = $('#pager');
        setConfigurationGrid();
        setGridData();
        setGridFooter();
    }

    function setConfigurationGrid() {

        grid.jqGrid({
            datatype: 'local',
            colNames: ['Column 1', 'Column 2'],
            colModel: [
                {name: 'column1', index: 'column1', editable: false, hidden: false, align: "center"},
                {name: 'column2', index: 'column2', editable: true, hidden: false, align: "center"}
            ],
            rowNum: 5,
            rowList: [5, 10, 15, 20],
            pager: pager,
            editurl: 'clientArray',
            sortname: 'column2',
            viewrecords: true,
            sortorder: 'asc',
            caption: 'Title grid',
            height: 'auto',
            width: 1024
        });
    }

    function setGridData() {
        grid.jqGrid('setGridParam', {data: data}).trigger("reloadGrid");
    }

    function setGridFooter() {
        grid.jqGrid('navGrid', '#pager',
                {edit: true, add: true, del: true, search: false, refresh: true}, //options 
                {beforeShowForm: setjqGridModalAddEditCenter, reloadAfterSubmit: false, onclickSubmit: onclickSubmitEdit, beforeSubmit: beforeSubmitEdit, afterSubmit: afterSubmitEdit, closeAfterEdit: true}, // edit options
                {beforeShowForm: setjqGridModalAddEditCenter, reloadAfterSubmit: false}, // add options 
                {beforeShowForm: setjqGridModalDeleteCenter, reloadAfterSubmit: false}, // del options 
                {} // search options );
        );
    }

    function onclickSubmitEdit(options, postdata) {
        console.log('Event onclickSubmit:');
        console.log(options);
        console.log(postdata);
    }

    function beforeSubmitEdit(postdata, formid) {
        console.log('Event beforeSubmit:');
        console.log(postdata);
        console.log(formid);
        return [true, 'Success'];
    }

    function afterSubmitEdit(response, postdata) {
        console.log('Event afterSubmit:');
        console.log(response);
        console.log(postdata);
        return [true, 'Success'];
        //return [false, 'Error'];
    }

    function setjqGridModalAddEditCenter(formid) {
        var editModal = $('#editmod' + grid[0].id),
                screenHeight = $(window).height(),
                screenWidth = $(window).width(),
                modalHeight = editModal.height(),
                modalWidth = editModal.width();

        editModal[0].style.top = (screenHeight / 2 - modalHeight) + 'px';
        editModal[0].style.left = (screenWidth / 2 - modalWidth / 2) + 'px';
    }

    function setjqGridModalDeleteCenter(formid) {
        var editModal = $('#delmod' + grid[0].id),
                screenHeight = $(window).height(),
                screenWidth = $(window).width(),
                modalHeight = editModal.height(),
                modalWidth = editModal.width();

        editModal[0].style.top = (screenHeight / 2 - modalHeight) + 'px';
        editModal[0].style.left = (screenWidth / 2 - modalWidth / 2) + 'px';
    }

    // public attributes and methods:
    this.initialize = initialize;
}

var indexCtrl = new IndexCtrl();
indexCtrl.initialize();