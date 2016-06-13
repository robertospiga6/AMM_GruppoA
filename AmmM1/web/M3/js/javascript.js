$(document).ready(function ()
{
   $("#ricerca").keyup(function()
    {  
        // Preleva il valore
        var text = $("#ricerca").val();
       
        $.ajax(
        {
            url: "filter.json",
            data:{
              chiave: "q",
              valore: text 
            },
            dataType: 'json',
            success : function(data, state){
                aggiornaListaProdotti(data);
            },
            error : function(data, state){
            }
        });
        
       // Funzione che viene richiamata in caso di successo
        function aggiornaListaProdotti(listaProdotti,idCliente){
            // Svuota la tabella in cliente.jsp             
            $("#tabella").show(); // perché per la ricerca precedente potrei averla nascosta
            $("#filtroVuoto").remove(); //nascondo il possibile messaggio d'errore precedente
            $(".disp").remove();
            $(".pari").remove();
            
            var table = document.getElementById("tabella");
            
            var i=0;
            
            
            var controllo=0; // variabile di controllo per capire se trovo o meno risultati
            
            for(var prodotto in listaProdotti) {
                controllo=1;
                
                // creo una nuova riga nella tabella 
                var riga = table.insertRow(); 
                if (i%2==0) {
                    riga.className='pari';
                }
                else {
                    riga.className='disp';
                }    
                i++;
                
                // inserisco le celle nella riga 
                var cell1 = riga.insertCell();
                var cell2 = riga.insertCell();
                var cell3 = riga.insertCell();
                var cell4 = riga.insertCell();
                var cell5 = riga.insertCell();

                // inserisco i campi per ogni cella
                cell1.innerHTML = listaProdotti[prodotto].nome;
                cell2.innerHTML = "<img src=\"M3/img/"+listaProdotti[prodotto].imgurl+"\" alt=\""+listaProdotti[prodotto].nome+"\" title=\""+listaProdotti[prodotto].nome+"\" height=\"90\">";
                cell3.innerHTML = listaProdotti[prodotto].pezzi;
                cell4.innerHTML = listaProdotti[prodotto].prezzo;
                cell5.innerHTML = "<a href=\"cliente.html?id="+listaProdotti[prodotto].idCliente+"&codProdotto="+listaProdotti[prodotto].cod+"\">"+listaProdotti[prodotto].nome+"</a>";
            }
            if (controllo==0) { // se il filtro non porta a risultati
                // nascondo la tabella 
                $("#tabella").hide();
                $(".content").append("<p id=\"filtroVuoto\">Nessun prodotto trovato con il filtro impostato.</p>");
            }
        }
    }); 
});