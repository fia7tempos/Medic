/** getXMLHttprequest*sendRequest()
 * new>javascript source 파일. 생성
 *  2. Ajax 에러잡기 힘듬(디버깅)
 *  3. try catch e e값 자동 할 당
 *  4. 전역변수  ; 현재 실행 프로그램에서 전체 사용하는 변수
 */

var xhr = null;


function sendRequest(url, params, callback, method){
	xhr = getXMLHttpRequest();
	
	var httpMethod = method ? method.toUpperCase() : 'GET'; //메서드 없을 때 기본 get
	if (httpMethod != 'GET' && httpMethod != 'POST'){	// 오타
		httpMethod = 'GET';
	} 
	var httpParams = ( params == null  || params == '') ? null : params;
	var httpUrl = url;//get 방식이면서 파라메터가 null이 아닌경우
	if (httpMethod == 'GET' && httpParams !=null){
		httpUrl = httpUrl +'?' + httpParams;
	}
	xhr.open(httpMethod, httpUrl, true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = callback;
	xhr.send(httpMethod =='POST' ? httpParams : null);
}

function getXMLHttpRequest(){
	if (window.ActiveXObject){//explorer 4~9지원
		var versions = [
		    'Msxml2.XMLHTTP.6.0',
		    'Msxml2.XMLHTTP.5.0',
		    'Msxml2.XMLHTTP.4.0',
		    'Msxml2.XMLHTTP.3.0',
		    'Msxml2.XMLHTTP.2.0',
		    'Msxml2.XMLHTTP',
		    'Microsoft.XMLHTTP'
		];
		for(var i = 0; i < versions.length; i++){
			try {//window 95 버전이 아닌이상 에러나기 힘듬.
				return  new ActiveXObject(i);	
			} catch (e) {}
		}	
	} else if (window.XMLHttpRequest){//etc, explorer 10...
		try {// 처리용량 많을때  에러나기 힘듦
			return  new XMLHttpRequest();
		} catch (e) {}
	} else {
		return null;
	}
}