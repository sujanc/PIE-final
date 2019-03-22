export class AuthLoginInfo {
    insurerLicense: string;
    password: string;
     constructor(insurerLicense: string, password: string) {
        this.insurerLicense = insurerLicense;
        this.password = password;
    }
}
