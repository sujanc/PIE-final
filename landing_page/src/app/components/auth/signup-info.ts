export class SignUpInfo {
    fullName: string;
    // phone: string;
    username: string;
    email: string;
    role: string[];
    password: string;
    gender: string;
    securityAnswer: string;
    age:number;


    constructor(fullname: string, username: string, email: string, password: string, gender: string, securityAnswer: string,age:number) {
        this.fullName = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.securityAnswer = securityAnswer;
        this.role = ['user'];
        this.age=age;
    }
}
