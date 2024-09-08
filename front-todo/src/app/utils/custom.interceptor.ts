import {
  HttpInterceptorFn
} from '@angular/common/http';

export const customInterceptor: HttpInterceptorFn = (request, next) =>  {
    request = request.clone()
    if (request.url.includes('/login')){
      // Se for a página de login, simplesmente continue com a solicitação sem modificá-la
      return next(request);
    }else{
      let localToken = localStorage.getItem('token');
        if (localToken){
          localToken = localToken.replace(/["']/g, ''); // Isso remove todas as aspas duplas na string
          request = request.clone({headers: request.headers.set('Authorization', 'Bearer '+ localToken)})
        }
        return next(request); 
      }
}
