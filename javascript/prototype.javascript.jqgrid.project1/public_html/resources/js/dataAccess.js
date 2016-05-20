function DataAccess() {

    var data;

    data = [
//        {id: 'sdf', column2: 'Text 1'},
//        {id: 2, column2: 'Text 2'},
//        {id: 3, column2: 'Text 3'},
//        {id: 4, column2: 'Text 4'},
//        {id: 5, column2: 'Text 5'},
//        {id: 6, column2: 'Text 6'},
//        {id: 7, column2: 'Text 7'},
//        {id: 8, column2: 'Text 8'},
//        {id: 9, column2: 'Text 9'},
//        {id: 10, column2: 'Text 10'},
//        {id: 11, column2: 'Text 11'},
//        {id: 12, column2: 'Text 12'},
//        {id: 13, column2: 'Text 13'},
//        {id: 14, column2: 'Text 14'},
//        {id: 15, column2: 'Text 15'},
//        {id: 16, column2: 'Text 16'},
//        {id: 17, column2: 'Text 17'},
//        {id: 18, column2: 'Text 18'},
//        {id: 19, column2: 'Text 19'},
//        {id: 20, column2: 'Text 20'}
    ];

    function getList() {
        return data;
    }

    function add(object) {
        data.push(object);
    }

    function updateObject(object) {

        var objectId;

        data.forEach(function (item) {
            try {
                objectId = parseInt(object.id);
                
                if (isNaN(objectId)) {
                    throw new Error('ID incorreto');
                }
                
                if (item.id === objectId) {
                    item.id = objectId;
                    item.column2 = object.column2;   
                }
            } catch (e) {
                throw e;
            }
        });
    }

    function printListConsole () {
        
        data.forEach(function (item) {
            console.log(item);
        });
    }

    function deleteById (objectId) {
        
        var newData = [];
        
        data.forEach(function (item) {
            if (item.id !== parseInt(objectId)) {
                newData.push(item);
            }
        });
        
        data  = newData;
    }
    
    this.add = add;
    this.deleteById = deleteById;
    this.getList = getList;
    this.updateObject = updateObject;
    this.printListConsole = printListConsole;
}