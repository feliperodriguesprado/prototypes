function IndexCtrl() {

    var dataAccess = new DataAccess();
    var grid;
    var pager;

    function initialize() {

        grid = $('#grid');
        pager = $('#pager');
        setConfigurationGrid();
        setGridData();
        setGridFooter();
    }

    function setConfigurationGrid() {

        grid.jqGrid({
            datatype: 'local',
            colNames: ['ID', 'Column 2'],
            colModel: [
                {name: 'id', index: 'id', editable: false, align: "center"},
                {name: 'column2', index: 'column2', editable: true, align: "center"}
            ],
            rowNum: 10,
            rowList: [10, 20, 30, 40, 50],
            pager: pager,
            editurl: 'clientArray',
            sortname: 'column2',
            viewrecords: true,
            sortorder: 'asc',
            caption: 'Title grid',
            height: 'auto',
            width: 1024,
            localReader: {
                root: "root",
                page: "pager",
                total: 'total',
                records: 'records',
                repeatitems: true,
                cell: "cell",
                id: "id",
                userdata: "userdata",
                subgrid: {root: "rows", repeatitems: true, cell: "cell"}
            }
        });
    }

    function setGridData() {
        grid.jqGrid('setGridParam', {data: dataAccess.getList()}).trigger("reloadGrid");
        grid.getGridParam('page');
        grid.getGridParam('records');
        grid.getGridParam('lastpage');
    }

    function setGridFooter() {
        grid.jqGrid('navGrid', '#pager',
                {edit: true, add: true, del: true, search: false, refresh: true}, //options 
                {beforeShowForm: setjqGridModalAddEditCenter, reloadAfterSubmit: false, onclickSubmit: onclickSubmitEdit, beforeSubmit: beforeSubmitEdit, afterSubmit: afterSubmitEdit, closeAfterEdit: true}, // edit options
                {beforeShowForm: setjqGridModalAddEditCenter, reloadAfterSubmit: false, afterSubmit: afterSubmitAdd, closeAfterAdd: true}, // add options 
                {beforeShowForm: setjqGridModalDeleteCenter, reloadAfterSubmit: false, afterSubmit: afterSubmitDelete, closeAfterDelete: true}, // del options 
                {} // search options );
        );
    }

    function afterSubmitAdd(response, postdata) {
        console.log('Event afterSubmit:');
        console.log(response);
        console.log(postdata);
        
        postdata.id = new Date().getTime();
        dataAccess.add({id: postdata.id, column2: postdata.column2});
        dataAccess.printListConsole();
        return [true, 'Success'];
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

        try {
            dataAccess.updateObject({id: postdata.false, column2: postdata.column2});
            dataAccess.printListConsole();
            return [true, 'Success'];
        } catch (e) {
            return [false, e.message];
        }
    }

    function afterSubmitDelete (response, postdata) {
        console.log('Event afterSubmit:');
        console.log(response);
        console.log(postdata);
        
        dataAccess.deleteById(postdata.id);
        
        return [true, 'Success'];
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