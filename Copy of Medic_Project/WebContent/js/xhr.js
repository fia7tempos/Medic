/** getXMLHttprequest*sendRequest()
 * new>javascript source 파일. 생성
 *  2. Ajax 에러잡기 힘듦(디버깅)
 *  3. try catch e e값 자동 할 당
 *  4. 전역변수  ; 현재 실행 프로그램에서 전체 사용하는 변수
 */

var ajax = {};
ajax.xhr = {};


ajax.xhr.Request = function sendRequest(url, params, callback, method){
   this.url = url;
   this.params = params;
   this.callback = callback;
   this.method = method;
   this.send();
} //생성자 함수 생성

ajax.xhr.Request.prototype = {
      
   getXMLHttpRequest : function() {
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
            try {//window 95 버전이 아닌이상 에러나기 힘듦.
               return  new ActiveXObject(i);   
            } catch (e) {}
         }   
      } else if (window.XMLHttpRequest){//etc, explorer 10...
         try {// 처리용량 많을때 .. 에러나기 힘듦
            return  new XMLHttpRequest();
         } catch (e) {}
      } else {
         return null;
      }
   },
   
   send:function() {
      this.xhr = this.getXMLHttpRequest(); // 별개로 동작하기 위해 안에 넣음
      
      var httpMethod = this.method ? this.method.toUpperCase() : 'GET'; //메서드 없을 때 기본 get
      if (httpMethod != 'GET' && httpMethod != 'POST'){   // 오타
         httpMethod = 'GET';
      } 
      var httpParams = ( this.params == null  || this.params == '') ? null : this.params;
      var httpUrl = this.url;//get 방식이면서 파라메터가 null이 아닌경우
      if (httpMethod == 'GET' && httpParams !=null){
         httpUrl = httpUrl +'?' + httpParams;
      }
      this.xhr.open(httpMethod, httpUrl, true);
      this.xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); //한글 깨지는 것을 방지
      
      var request = this;
      this.xhr.onreadystatechange = function() {
         request.cbf.call(request);
      }
      this.xhr.send(httpMethod =='POST' ? httpParams : null);
   },
   
   cbf : function() {
      this.callback(this.xhr); //나 자신의 파라메터 xhr을 넘김
   }
}



