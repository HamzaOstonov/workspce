          function createPrintIframe(iframeName, width, height) {
            var iframe = document.getElementById(iframeName);
            if (iframe) {
                var iframeDoc;
                if (iframe.contentDocument) {
                  iframeDoc = iframe.contentDocument;
                }
                else if (iframe.contentWindow) {
                  iframeDoc = iframe.contentWindow.document;
                }
                else if (window.frames[iframe.name]) {
                  iframeDoc = window.frames[iframe.name].document;
                }
                if (iframeDoc) {
                  iframeDoc.body.innerHTML = "";
                }

              return iframe;
            }

            var iframe;
            if (document.createElement && (iframe =        document.createElement('iframe'))) {
              iframe.name = iframe.id = iframeName;
              iframe.width = width;
              iframe.height = height;
              iframe.src = 'about:blank';
              iframe.frameBorder = '0';
              document.body.appendChild(iframe);
            }
            return iframe;
          }

          function printMunicipalCheque(chequeHtml) {
            var iframe = createPrintIframe('iframeprt', 0, 0);
            setTimeout(function () {
              if (iframe) {
                var iframeDoc;
                if (iframe.contentDocument) {
                  iframeDoc = iframe.contentDocument;
                }
                else if (iframe.contentWindow) {
                  iframeDoc = iframe.contentWindow.document;
                }
                else if (window.frames[iframe.name]) {
                  iframeDoc = window.frames[iframe.name].document;
                }
                if (iframeDoc) {
                  iframeDoc.open();
                  iframeDoc.write(chequeHtml);
                  iframeDoc.close();
                }

                window.frames[iframe.name].focus();
                window.frames[iframe.name].print();
              }

            }, 10);
          }
          
          function printWnd() {
             // var iframe = document.getElementById('rpframe');
        	  var iframe = zk.Widget.$("$rpframe");

        	  
        	 // iframe.focus();
        	 // iframe.print();

        	 // iframe.src.print();
        	 // setTimeout(function () {
            	  //iframe.focus();
            	  //iframe.print();
                  //window.frames[0].focus();
                 // window.frames[0].print();
                  window.frames[1].focus();
                  window.frames[1].print();

 
                  //window.frames[iframe.name].focus();
                  //window.frames[iframe.name].print();
            //  }, 10);

              
            }