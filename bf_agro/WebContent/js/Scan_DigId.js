function Scan_DigId() {
    var xhr = new XMLHttpRequest();    
    var result = null;
    
    try {
        xhr.open('GET', 'http://localhost:50000/api/Identification/DataFromForm', false);
        xhr.send();        

        if (xhr.status == 200) {
            result = xhr.responseText;
        }
        else {
            alert('Не удалос завершить операцию!');            
        }
    } catch (error) {
        if (error.code == 19) // "NetworkError"
        {
            alert(`Нет соединение с DigID!
              Возможные причины:
              1. Вы не запустили ПО Scanner паспорт DigID;
              2. ВЫ не установили ПО DigID ;             
            `);
        }       
    }
    
    zAu.send(new zk.Event(zk.Widget.$('$clientmain'), 'onAccept', result, { toServer: true }));
}