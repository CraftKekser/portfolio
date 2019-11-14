<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
        <script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>

        <script></script>

        <title>Teilchenzoo</title>

        <style>
            html{
                height: 90%;
            }
            body{
                height: 90%;
                background: linear-gradient(170deg, #fff, #fff, #eee, #eee);
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed;
            }
            h2{
                text-shadow: 2px 2px 2px rgba(0,0,0,0.2);
                margin: 16px;
            }
            h4{
                margin-top: 7px;
            }

            hr{
                border: 0;
                height: 1px;
                background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
            }

            .model-container{
                height: 100%;
                width: 100%;
            }

            .model-content-container{
                height: 100%;
                width: 30%;
                margin: 1%;
                padding: 6px;
                display: inline-table;
                box-shadow: 1px 1px 6px rgba(10,10,10,0.3);
            }

            .sub-content{
                margin-left: 15px;
                margin-right: 15px;
                padding: 14px;
                padding-top: 7px;
                text-align: left;
                border-radius: 4px;
                display: inline-table;
            }

            .bosonen{
                background-color: #9966ff;
            }
            .hadronen{
                background-color: #ffcc66;
            }
            .fermionen{
                background-color: #99cc00;
            }
            .leptonen{
                background: linear-gradient(to top, rgba(9, 132, 227,1.0) 93%, rgba(8, 115, 196,1.0));
                margin-left: 0px;
                margin-right: 0px;
                border-top-left-radius: 0px;
                border-bottom-left-radius: 0px;
            }
            .quarks{
                background: linear-gradient(to top, rgba(225, 112, 85,1.0) 93%, rgba(195, 67, 34,1.0));
                margin-left: 0px;
                margin-right: 0px;
                border-top-right-radius: 0px;
                border-bottom-right-radius: 0px;
            }
            .imgmarg{
                margin: 3px;
            }
            .clickable{
                transition: filter 0.8s;
                cursor: pointer;
            }
            .clickable:hover{
                transition: filter 0.2s;
                filter: brightness(1.3);
            }
            .hadron{
                transition: background-color 0.8s;
                height: 50px;
                width: 50px;
                margin: 3px;
                line-height: 45px;
                background-color: #c2c2c2;
                font-size: 14pt;
                font-weight: bold;
                border: 1px solid #7a7a7a;
                border-radius: 50%;
                cursor: pointer;
            }
            .hadron:hover{
                transition: background-color 0.2s;
                background-color: #e1e1e1;
            }
            .mesonen-hint{
                background: linear-gradient(to right, rgba(0, 0, 0,0.0) 60%, rgba(153, 102, 255,0.3));
            }
            .baryonen-hint{
                background: linear-gradient(to left, rgba(0, 0, 0,0.0) 60%, rgba(153, 204, 0,0.3));
            }

            .popup-background {
                position: absolute;
                top: 0px;
                bottom: 0px;
                left: 0px;
                right: 0px;
                background-color: rgba(10,10,10,0.7);
            }
            .popup-background .popupinfo{
                position: absolute;
                background-color: #eee;
                top: 17%;
                bottom: 20%;
                left: 20%;
                right: 20%;
                overflow: auto;
                padding: 4px;
                box-shadow: 3px 3px 30px rgba(0,0,0,0.4);
            }
            .popup-background .popupinfo .popupinfo-content{
                padding: 14px;
                padding-top: 0px;
            }

            .popup-background .closed{
                transition: background-color 0.2s;
                position: absolute;
                top: 17%;
                right: 16%;
                width: 40px;
                height: 40px;
                text-align: center;
                line-height: 37px;
                font-weight: bold;
                color: #ddd;
                border: 1px solid #ddd;
                border-radius: 50%;
                cursor: pointer;
            }
            .popup-background .closed:hover{
                transition: background-color 0.2s;
                background-color: rgba(220, 0, 0, 0.7);
            }
            .foot{
                position: absolute;
                bottom: 0px;
                left: 0px;
                right: 0px;
                background-color: rgba(10,10,10,0.2);
                text-align: center;
                font-size: 11pt;
                padding: 2px;
            }
        </style>


    </head>
    <body>

        <h2>Das Standardmodell der Elementarteilchen <img height="42" class="clickable" onclick="showPopup(this)" data-name="general" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Info_icon-72a7cf.svg/600px-Info_icon-72a7cf.svg.png"/></h2>

    <center>
        <div class="model-container">
            <div class="bosonen model-content-container">
                <h4>Bosonen</h4>
                <hr/>
                <img src="http://media.lukas-10100.de/teilchenzoo/higgs.png" onclick="showPopup(this)" data-name="higgsboson" class="imgmarg clickable" height="100" title="Higgs-Boson" alt="Higgs-Boson"/>
                <img src="http://media.lukas-10100.de/teilchenzoo/graviton.png" onclick="showPopup(this)" data-name="graviton" class="imgmarg clickable" height="100" title="Higgs-Boson" alt="Higgs-Boson"/>
                <br/><br/>     
                <h6>Eichbosonen</h6>
                <img src="http://media.lukas-10100.de/teilchenzoo/photon.png" onclick="showPopup(this)" data-name="photon" title="Photon" height="80" alt="Photon" class="imgmarg clickable"/>
                <img src="http://media.lukas-10100.de/teilchenzoo/gluon.png" onclick="showPopup(this)" data-name="gluon" title="Gluon" height="80" alt="Gluon" class="imgmarg clickable"/>
                <img src="http://media.lukas-10100.de/teilchenzoo/w+_boson.png" onclick="showPopup(this)" data-name="wplus" title="W+ Boson" height="80" alt="W+ Boson" class="imgmarg clickable"/>
                <img src="http://media.lukas-10100.de/teilchenzoo/w-_boson.png" onclick="showPopup(this)" data-name="wminus" title="W- Boson" height="80" alt="W- Boson" class="imgmarg clickable"/>
                <img src="http://media.lukas-10100.de/teilchenzoo/z_boson.png" onclick="showPopup(this)" data-name="zboson" title="Z Boson" height="80" alt="Z Boson" class="imgmarg clickable"/>
                <br/><br/>
            </div>
            <div class="hadronen model-content-container">
                <h4>Hadronen</h4>
                <hr/>
                <div class="row">
                    <div class="col-6 mesonen-hint">
                        <h6>Mesonen</h6>
                        <small>Pseudoskalar <i>(Spin \(0\))</i></small>
                        <div class="hadron" title="Pion" onclick="showPopup(this)" data-name="mesonen/pion">\(\pi\)</div>
                        <div class="hadron" title="Kaon" onclick="showPopup(this)" data-name="mesonen/kaon">\(K\)</div>
                        <div class="hadron" title="Eta" onclick="showPopup(this)" data-name="mesonen/eta">\(\eta\)</div>
                        <div class="hadron" title="D-Meson" onclick="showPopup(this)" data-name="mesonen/dmeson">\(D\)</div>
                        <div class="hadron" title="B-Meson" onclick="showPopup(this)" data-name="mesonen/bmeson">\(B\)</div>
                        <small>Vektormesonen <i>(Spin \(1\))</i></small>
                        <div class="hadron" title="Roh" onclick="showPopup(this)" data-name="mesonen/roh">\(\rho\)</div>
                        <div class="hadron" title="Kaon" onclick="showPopup(this)" data-name="mesonen/kaon">\(K^*\)</div>
                        <div class="hadron" title="Omega" onclick="showPopup(this)" data-name="mesonen/omega">\(\omega\)</div>
                        <div class="hadron" title="J/Psi" onclick="showPopup(this)" data-name="mesonen/psi">\(\psi\)</div>
                        <div class="hadron" title="D-Meson" onclick="showPopup(this)" data-name="mesonen/dmeson">\(D^*\)</div>
                        <div class="hadron" title="Ypsilon" onclick="showPopup(this)" data-name="mesonen/ypsilon">\(\Upsilon\)</div>
                    </div>
                    <div class="col-6 baryonen-hint">
                        <h6>Baryonen</h6>
                        <small><i>Spin \(1/2\)</i></small>
                        <div class="hadron" title="Proton" onclick="showPopup(this)" data-name="baryonen/proton">\(p\)</div>
                        <div class="hadron" title="Neutron" onclick="showPopup(this)" data-name="baryonen/neutron">\(n\)</div>
                        <div class="hadron" title="Lambda" onclick="showPopup(this)" data-name="baryonen/lambda">\(\Lambda\)</div>
                        <div class="hadron" title="Sigma" onclick="showPopup(this)" data-name="baryonen/sigma">\(\Sigma\)</div>
                        <div class="hadron" title="Xi" onclick="showPopup(this)" data-name="baryonen/xi">\(\Xi\)</div>
                        <div class="hadron" title="Charmed Lambda" onclick="showPopup(this)" data-name="baryonen/charmedlambda">\(\Lambda_c^+\)</div>
                        <small><i>Spin \(3/2\)</i></small>
                        <div class="hadron" title="Delta" onclick="showPopup(this)" data-name="baryonen/delta">\(\Delta\)</div>
                        <div class="hadron" title="Sigma" onclick="showPopup(this)" data-name="baryonen/sigma">\(\Sigma^*\)</div>
                        <div class="hadron" title="Xi" onclick="showPopup(this)" data-name="baryonen/xi">\(\Xi^*\)</div>
                        <div class="hadron" title="Omega" onclick="showPopup(this)" data-name="baryonen/omega">\(\Omega^-\)</div>
                    </div>
                </div>
                <br/>
            </div>
            <div class="fermionen model-content-container">
                <h4>Fermionen</h4>
                <hr/>
                <div class="sub-content">
                    <span style="float: left; font-style: italic; margin-top: 58px">1. Generation</span><br/>
                    <span style="float: left; font-style: italic; margin-top: 88px">2. Generation</span><br/>
                    <span style="float: left; font-style: italic; margin-top: 88px">3. Generation</span><br/>
                </div>
                <div class="quarks sub-content">
                    <h6>Quarks</h6>
                    <center>
                        <img src="http://media.lukas-10100.de/teilchenzoo/up_quark.png" onclick="showPopup(this)" data-name="up" title="Up" height="80" alt="Up" class="clickable"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/down_quark.png" onclick="showPopup(this)" data-name="down" title="Down" height="80" alt="Down" class="clickable"/>
                        <hr class="bg-dark"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/charm_quark.png" onclick="showPopup(this)" data-name="charm" title="Charm" height="80" alt="Charm" class="clickable"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/strange_quark.png" onclick="showPopup(this)" data-name="strange" title="Strange"  height="80" alt="Strange" class="clickable"/>
                        <hr class="bg-dark"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/top_quark.png" onclick="showPopup(this)" data-name="top" title="Top" height="80" alt="Top" class="clickable"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/bottom_quark.png" onclick="showPopup(this)" data-name="bottom" title="Bottom" height="80" alt="Bottom" class="clickable"/>
                        <br/>
                    </center>
                </div>
                <div class="leptonen sub-content">
                    <h6>Leptonen <small>+ Neutrinos</small></h6>
                    <center>
                        <img src="http://media.lukas-10100.de/teilchenzoo/elektron.png" onclick="showPopup(this)" data-name="elektron" height="80" alt="Elektron" title="Elektron" class="clickable"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/elektron_neutrino.png" onclick="showPopup(this)" data-name="eneutr" height="80" alt="Elektron-Neutrino" title="Elektron-Neutrino" class="clickable"/>
                        <hr class="bg-dark"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/myon.png" onclick="showPopup(this)" data-name="myon" height="80" alt="Myon" title="Myon" class="clickable"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/myon_neutrino.png" onclick="showPopup(this)" data-name="mneutr" height="80" alt="Myon-Neutrino" title="Myon-Neutrino" class="clickable"/>
                        <hr class="bg-dark"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/tauon.png" onclick="showPopup(this)" data-name="tauon" height="80" alt="Tauon" title="Tauon" class="clickable"/>
                        <img src="http://media.lukas-10100.de/teilchenzoo/tau_neutrino.png" onclick="showPopup(this)" data-name="tneutr" height="80" alt="Tau-Neutrino" title="Tau-Neutrino" class="clickable"/>
                        <br/>
                    </center>
                </div>
            </div>
        </div>
    </center>
    
    <div class="foot">
        &copy; Lukas Wolfrum 2019 • Zuletzt aktualisiert: 02.11.2019 • <a href="#" onclick="showPopup(this)" data-name="quellen">Quellenverzeichnis</a>
    </div>


    <div id="popup" class="popup-background" hidden>
        <div id="closePopup" class="closed">X</div>
        <div class="popupinfo">
            <div id="popupContent" class="popupinfo-content">
                <h2 id="popupTitle"></h2>
                Bitte warten...
            </div>
        </div>
    </div>

    <script>
        function showPopup(element) {
            document.getElementById("popup").hidden = false;
            var nam = $(element).data("name");
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.status === 200 && this.readyState === 4) {
                    document.getElementById("popupContent").innerHTML = this.responseText;
                    MathJax.typesetPromise();
                }
            };
            xhttp.open("GET", "req.php?file=info/" + nam + ".txt");
            xhttp.send();
        }
        function hidePopup() {
            document.getElementById("popup").hidden = true;
        }

        window.onload = function () {
            $(function () {
                $('[data-toggle="popover"]').popover()
            });
            $('#popup').on('show', function () {
                alert("do something");
            });
            document.getElementById("closePopup").onclick = function () {
                hidePopup();
            };
        };
    </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>