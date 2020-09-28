import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {JwtHelperService} from "@auth0/angular-jwt";
import {BehaviorSubject, Subject} from "rxjs";
import {Userlogger} from "../model/model.userlogger";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";


@Injectable({
  providedIn:'root'
})
export class AuthService{
  user=new BehaviorSubject<Userlogger>(null);

  private username:any;
  private expiration:any;
  private roles:Array<any>;
  private jwtToken=null;
  private tokenExpirationTimer:any;
  private isAdmin:boolean;
  utilisateur:any=null;

constructor(private http:HttpClient,public router:Router,public toastr: ToastrService) {
}

login(dataForm){
  return this.http.post("http://localhost:8080/login",dataForm,{observe:'response'});

}

saveToken(jwt:string){
  this.jwtToken=jwt;
  localStorage.setItem('token',jwt);//token
  let jwtHelper= new JwtHelperService();
  this.roles=jwtHelper.decodeToken(this.jwtToken).roles;
  this.isAdmin=false;
  this.roles.forEach(r=>{
    if(r.authority==="ADMIN"){
      this.isAdmin=true;
    }
  });
  this.expiration=jwtHelper.decodeToken(this.jwtToken).exp;
  this.username=jwtHelper.decodeToken(this.jwtToken).sub;
  const date =new Date(0);
  const expirationDate=date.setUTCSeconds(this.expiration);
  const user=new Userlogger(null,this.username,this.roles,this.jwtToken,expirationDate,this.isAdmin);
  this.user.next(user);
  this.http.get("http://localhost:8080/loadcurrentuser?username="+this.username).subscribe(data=>{
    this.utilisateur=data;
    this.user.getValue().utilisateur=this.utilisateur;
    localStorage.setItem('userData',JSON.stringify(user));

  },error => {
    console.log(error);
  });
  this.autoLogout(expirationDate.valueOf()-new Date().valueOf());


}


loadToken(){
  this.jwtToken=localStorage.getItem('token')
}


autoLogin(){
  const userData:{
    utilisateur:any;
    username:string;
    roles:any;
    _token:string;
    _expiration:any;
    isAdmin:boolean;
  }=JSON.parse(localStorage.getItem('userData'));

  if(!userData){
    return;
  }
  const loadedUser=new Userlogger(userData.utilisateur,userData.username,userData.roles,userData._token,userData._expiration,userData.isAdmin);
  if(loadedUser.token){
    this.user.next(loadedUser);
    this.autoLogout(userData._expiration.valueOf()-new Date().valueOf());
  }
}

logout(){
    this.user.next(null);
    this.router.navigate(['/login']);
    localStorage.removeItem('userData');
    localStorage.removeItem('token');
    if(this.tokenExpirationTimer){
      clearTimeout(this.tokenExpirationTimer);
    }
  this.tokenExpirationTimer=null;
  }

autoLogout(expirationDuration:number){
  this.tokenExpirationTimer= setTimeout(()=>{
   // this.toastr.warning('Token expired');
    this.logout();
  },expirationDuration)
}

  loadCurrentUserId(username:string){
    return this.http.get("http://localhost:8080/loadcurrentuser?username="+username).subscribe(data=>{
      this.utilisateur=data;
      this.user.getValue().utilisateur=this.utilisateur;
    },error => {
      console.log(error);
    });

  }

}
