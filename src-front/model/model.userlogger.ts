export class Userlogger {
  constructor(
    public utilisateur:any,
    public username:string,
    public roles:any,
    private _token:string,
    private _expiration:any,
    public isAdmin:boolean) {}

    get token(){
    if(!this._expiration || new Date().valueOf() > this._expiration.valueOf()){
      return null;
    }

     return this._token;
  }
}
