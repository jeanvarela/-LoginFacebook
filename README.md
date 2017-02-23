# LoginFacebook

    Nesse repositório tem a configuração miníma para fazer em login de app android utilizando o login do facebook.
    Para utilizar o login no seu projeto é necessário realizar as seguintes alterações
	  
    As duas modificações necessárias são realizadas no arquivo values/string.xml
	    O valor de facebook_app_id deve ser substituido pelo id do seu aplicativo no facebook.
		O valor de fb_login_protocol_scheme deve ser modificado.
	    <string name="facebook_app_id">IDAPP</string>
		<string name="fb_login_protocol_scheme">fbID</string>