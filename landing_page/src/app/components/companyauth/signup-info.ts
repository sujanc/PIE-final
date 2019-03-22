export class SignUpInfo {
    insurerName: string;
    // phone: string;
    insurerLicense: string;
    insurerEmail: string;
    role: string[];
    password: string;
    insurerSecurityQuestion: string;
    insurerSecurityAnswer: string;

    constructor(insurerName: string, insurerLicense: string, insurerEmail: string,
        password: string, insurerSecurityQuestion: string, insurerSecurityAnswer: string) {
        this.insurerName = insurerName;
        this.insurerLicense = insurerLicense;
        this.insurerEmail = insurerEmail;
        this.password = password;
        this.insurerSecurityAnswer = insurerSecurityAnswer;
        this.insurerSecurityQuestion = insurerSecurityQuestion;
        this.role = ['user'];
    }
}
