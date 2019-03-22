export class PolicyFormInfo {
    public  policyId: Number;
  public  insurerName : string;
  public  policyName: string;
  public  members:string;
  public  sumInsured:Number;
  public  noOfCashLessHospitals:Number;
  //public  cashLessHospitals:Array<Hospital>;
  public  monthlyPremium:Number;
  public  yearlyPremium:Number;
  //public  diseasesCovered:Array<Disease>;
  public  location:string;
  public  minAge:Number;
  public  maxAge:Number;
  public  waitingPeriod:Number;
 // public  createdAt:Timestamp;
  //public  updatedAt:Timestamp;
 // public  createdBy:string;
 // public  updatedBy:string;

 
    constructor( policyId: Number, insurerName : string, policyName: string,
        members:string, sumInsured:Number, noOfCashLessHospitals:Number,
        monthlyPremium:Number,yearlyPremium:Number,location:string, minAge:Number,
        maxAge:Number, waitingPeriod:Number) {
        this.policyId = policyId;
        this.insurerName = insurerName;
        this.policyName = policyName;
        this.members = members;
        this.sumInsured = sumInsured;
        this.noOfCashLessHospitals = noOfCashLessHospitals;
        //this.role = ['user'];
        this.monthlyPremium = monthlyPremium;
        this.yearlyPremium = yearlyPremium;
        this.location = location;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.waitingPeriod = waitingPeriod;
        //this.createdBy = createdBy;
        //this.updatedBy = updatedBy;
        
    }
 }