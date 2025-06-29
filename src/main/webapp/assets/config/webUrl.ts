export var webUrl = {

  /*
  host: 'http://192.168.40.101:8080/sgiid',
  mod_pro: true,
  host_app: 'http://192.168.40.101:8080/app',
  link: 'http://192.168.40.101:5050/dev/sgiid/'

  /*
  host: 'http://localhost:8080/sgiid',
  host_app: 'http://localhost:8080/app',
  mod_pro: false,
*//* 
    mod_pro: false,
    host_app: 'http://192.168.40.126:8080/app',
    host: 'http://192.168.40.126:8080/sgiid',
    host_documentos: 'http://192.168.40.126:8080/documentos',
    link: location.protocol + '//' + location.host.replace('4200', '5050').replace('8080', '5050') + '/dev/sgiid/',
    host_dashboard: 'http://192.168.40.101:8080/fr_formariz/rest/rest',
    host_manutencao: 'http://192.168.40.126:8080/app_manutencao/#/painel_pendentes',
    host_manutencao2: 'http://192.168.40.126:8080/app_manutencao/#/painel_controlo_view',
    middleware_alfresco: 'http://192.168.40.107:3000/alfresco',
    maquinas_opc: 'http://192.168.40.107:3001'
    /* */
  mod_pro: true,
  host_app: location.protocol + '//' + location.host.replace('4200', '8080') + '/app',
  host: location.protocol + '//' + location.host.replace('4200', '8080') + '/sgiid',
  host_documentos: location.protocol + '//' + location.host.replace('4200', '8080') + '/documentos',
  link: location.protocol + '//' + location.host.replace('4200', '5050').replace('8080', '5050') + '/dev/sgiid/',
  host_dashboard: location.protocol + '//' + location.host.replace('4200', '8080') + '/fr_formariz/rest/rest',
  host_manutencao: location.protocol + '//' + location.host.replace('4200', '8080') + '/app_manutencao/#/painel_pendentes',
  host_manutencao2: location.protocol + '//' + location.host.replace('4200', '8080') + '/app_manutencao/#/painel_controlo_view',
  middleware_alfresco: 'http://192.168.40.107:3000/alfresco',
  maquinas_opc: 'http://192.168.40.107:3001'

  /**/
}
