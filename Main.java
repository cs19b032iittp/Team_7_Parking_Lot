package com.company;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalTime;

class Spot {
    private int spotNo;
    private boolean isElectric;
    private boolean isReserved;
    public Spot(int spotNo, boolean isElectric) {
        this.spotNo = spotNo;
        this.isElectric = isElectric ;
    }
    public int getSpotNo() {
        return this.spotNo;
    }
    public boolean isElectric() {
        return this.isElectric ;
    }
    public void setElectric(boolean status) {
        this.isElectric  = status;
    }
    public boolean isReserved() {
        return this.isReserved;
    }
    public void setReservedStatus(boolean status) {
        this.isReserved = status;
    }
}

class Floor {
    private int id;
    private String name;
    private int numberOfSpots;
    private Spot[] r;
    private ArrayList<Spot> rAl = new ArrayList<Spot>();
    public Floor(int id, String name, int numberOfSpots) {
        this.id = id;
        this.name = name;
        this.numberOfSpots = numberOfSpots;
        this.r = new Spot[numberOfSpots];
        for(int i=0; i<numberOfSpots; i++) {
            this.r[i] = new Spot(i+1, false);
        }

    }
    public Spot[] getAvailableSpots() {
        this.rAl.clear();
        for (int i = 0; i < numberOfSpots; i++) {
            if (!this.r[i].isReserved()) {
                this.rAl.add(r[i]);
            }
        }
        return this.rAl.toArray(new Spot[0]);
    }
    public Spot[] getAllSpots() {
        return r;
    }
    public Spot getSpotById(int spotNo) {
        if(this.isInputValid(spotNo)) {
            for(int i=0; i<numberOfSpots; i++){
                if(this.r[i].getSpotNo() == spotNo)
                    return this.r[i];
            }
        }
        return null;
    }
    public boolean ModifySpotElectric(int spotNo, boolean status) {
        if(this.isInputValid(spotNo)){
            for(int i=0; i<numberOfSpots; i++){
                if(this.r[i].getSpotNo() == spotNo){
                    this.r[i].setElectric(status);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean addSpot(int spotNo) {
        this.rAl.clear();
        if(spotNo>=0){
            for(int i=0; i<numberOfSpots; i++){
                this.rAl.add(r[i]);
                if(this.r[i].getSpotNo() == spotNo){
                    return false;
                }
            }
            this.rAl.add(new Spot (spotNo, false));
            this.numberOfSpots++;
            this.r = this.rAl.toArray(new Spot[0]);
            return true;
        }
        return false;
    }
    public boolean removeSpot(int spotNo) {
        if (this.isInputValid(spotNo)) {
            this.r = Arrays.stream(this.r).filter(x -> x.getSpotNo() != spotNo).toArray(Spot[]::new);
            if (this.r.length != this.numberOfSpots) {
                this.numberOfSpots--;
                return true;
            }
        }
        return false;
    }
    public boolean bookRegularSpot() {
        for(int i=0; i<numberOfSpots; i++){
            if(!this.r[i].isReserved() && !this.r[i].isElectric()){
                this.r[i].setReservedStatus(true);
                return true;
            }
        }
        return false;
    }
    public boolean bookRegularSpot(int spotNo) {
        if(this.isInputValid(spotNo)){
            for(int i=0; i<numberOfSpots; i++){
                if(this.r[i].getSpotNo() == spotNo && !this.r[i].isElectric()){
                    this.r[i].setReservedStatus(true);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bookElectricSpot() {
        for(int i=0; i<numberOfSpots; i++){
            if(!this.r[i].isReserved() && this.r[i].isElectric()){
                this.r[i].setReservedStatus(true);
                return true;
            }
        }
        return false;
    }
    public boolean bookElectricSpot(int spotNo) {
        if(this.isInputValid(spotNo)){
            for(int i=0; i<numberOfSpots; i++){
                if(this.r[i].getSpotNo() == spotNo && this.r[i].isElectric()){
                    this.r[i].setReservedStatus(true);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isElectricSpotAvailable() {
        for(int i=0; i<numberOfSpots; i++){
            if(!this.r[i].isReserved() && this.r[i].isElectric())
                return true;
        }
        return false;
    }

    private boolean isInputValid(int i){
        return i>=0 && i<=this.numberOfSpots;
    }
}

public class Main {
    public static void main(String[] args) {
        Floor h = new Floor(001, "Cars", 5);
        Spot[] r = h.getAvailableSpots();
        Spot[] ra = h.getAllSpots();
        Scanner sc = new Scanner(System.in);
        entrypanel obj=new entrypanel();

        int T = 5;
        System.out.println("Sign In \n1.Admin \n2.Customer ");
        while(T == 5) {
            int signin = sc.nextInt();
            if(signin == 1){
                System.out.println("Enter Password");
                String Passcode = "Charan123";
                String Password = sc.next();
                if(Passcode.equals(Password)){
                    System.out.println("Select an option ");
                    System.out.println("1.All Unreserved spots");
                    System.out.println("2.Get spot by ID: Existing");
                    System.out.println("3.All spots");
                    System.out.println("4.Adding a spot valid case");
                    System.out.println("5.Convert 2 spots to Electric");
                    System.out.println("6.Count Electric spots");
                    System.out.println("7.Book electric spots Valid and Invalid");
                    System.out.println("8.Book 2 regular spots Valid and Invalid");
                    System.out.println("9.Get number available spots");
                    System.out.println("10.Delete a Spot");
                    System.out.println("11.Delete a non existing spot");
                    System.out.println("12.Book a Spot");
                    System.out.println("13.Available Spots");
                    int Option = sc.nextInt();
                    if(Option == 1  ){
                        System.out.println("\nAll Unreserved spots");
                        for(int i=0; i<r.length; i++) {
                            System.out.println(r[i]);
                        }
                    }

                    else if(Option == 2 ){
                        System.out.println("\nGet spot by ID: Existing");
                        System.out.println(h.getSpotById(3));
                    }
                    else if(Option ==  3 ){
                        System.out.println("\nAll spots");
                        for(int j=0; j<ra.length; j++) {
                            System.out.println(ra[j].toString() + ra[j].getSpotNo());
                        }
                    }
                    else if(Option ==  4 ){
                        System.out.println("\nAdding a spot valid case");
                        System.out.println(h.addSpot(7));
                        System.out.println("Total Number of spots " + h.getAllSpots().length);
                    }
                    else if(Option == 5  ){
                        System.out.println("\nConvert 2 spots to Electric");
                        r = h.getAvailableSpots();
                        r[0].setElectric(true);
                        r[1].setElectric(true);
                    }
                    else if(Option ==  6){
                        System.out.println("\nCount Electric spots");
                        r = h.getAvailableSpots();
                        for(int i=0; i<r.length; i++){
                            if(r[i].isElectric())
                                System.out.println("Electric");
                        }
                    }
                    else if(Option == 7){
                        System.out.println("\nBook electric spots Valid and Invalid");
                        System.out.println(h.bookElectricSpot());
                        System.out.println(h.bookElectricSpot(-6));
                    }
                    else if(Option == 8){
                        System.out.println("\nBook 2 regular spots Valid and Invalid");
                        System.out.println(h.bookRegularSpot());
                        System.out.println(h.bookRegularSpot(-9));
                    }
                    else if(Option == 9){
                        System.out.println("Get number available spots");
                        r = h.getAvailableSpots();
                        System.out.println("Number of available spots " + r.length);
                    }
                    else if(Option ==10 ){
                        System.out.println("\nDelete a Spot");
                        System.out.println(h.removeSpot(2));
                        System.out.println("Number of spots "  + h.getAllSpots().length);
                        System.out.println("Number of spots available "  + h.getAvailableSpots().length);
                    }
                    else if(Option == 11 ){
                        System.out.println("\nDelete a non existing spot");
                        System.out.println(h.removeSpot(-1));
                        r = h.getAllSpots();
                        System.out.println("Number of spots " + r.length);
                    }
                    else if(Option == 12){
                        System.out.println("\nBook a  spot");
                        int bookspot = sc.nextInt();
                        ra[bookspot].setReservedStatus(true);
                    }
                    else if(Option == 13){
                        System.out.println("Print available spots");
                        r = h.getAvailableSpots();
                        for(int i=0; i<r.length; i++){
                            System.out.println(r[i]);
                        }
                    }
                    else{
                        System.out.println("Wrong Input");
                    }



                }
                else{
                    System.out.println("Wrong Password");
                }

            }
            else if(signin == 2){
                System.out.println("Select an option ");
                System.out.println("1.All Unreserved spots");
                System.out.println("2.Book electric spots Valid and Invalid");
                System.out.println("3.Book 2 regular spots Valid and Invalid");
                System.out.println("4.Get number available spots");
                System.out.println("5.Book a Spot");
                System.out.println("6.Available Spots");
                System.out.println("7.Exit");
                int Option = sc.nextInt();
                if(Option == 1){
                    System.out.println("\nAll Unreserved spots");
                    for(int i=0; i<r.length; i++) {
                        System.out.println(r[i]);
                    }
                }
                else if(Option == 2){
                    System.out.println("\nBook electric spots Valid and Invalid");
                    System.out.println(h.bookElectricSpot());
                    System.out.println(h.bookElectricSpot(-6));
                }
                else if(Option == 3){
                    System.out.println("\nBook 2 regular spots Valid and Invalid");
                    System.out.println(h.bookRegularSpot());
                    System.out.println(h.bookRegularSpot(-9));
                }
                else if(Option == 4 ){
                    System.out.println("Get number available spots");
                    r = h.getAvailableSpots();
                    System.out.println("Number of available spots " + r.length);
                }
                else if(Option == 5){
                    String type;
                    //motorbike, car,truck,electric,van;
                    String name,vehicle_no;
                    int mobile_no;
                    type=sc.next();//enter vehicle type
                    name=sc.next();//enter customers name
                    vehicle_no=sc.next();//enter vehicle no
                    mobile_no=sc.nextInt();//enter customers mobile_no
                    obj.entrypanel(name, mobile_no, type, vehicle_no);
                    System.out.println("\nBook a  spot");
                    int bookspot = sc.nextInt();
                    ra[bookspot].setReservedStatus(true);
                }
                else if(Option == 6){
                    System.out.println("Print available spots");
                    r = h.getAvailableSpots();
                    for(int i=0; i<r.length; i++){
                        System.out.println(ra[i].getSpotNo());
                    }
                }else if(Option == 7) {
                    Farecontroller e = new Farecontroller();
                    e.input();
                    e.calculate();
                    e.display();
                    e.cashpayment();
                }
                else{
                    System.out.println("Wrong Input Entered");
                }
            }
            else{
                System.out.println("Wrong Input Entered");
            }
            System.out.println("run or terminate");
            T = sc.nextInt();
        }
    }

}
 class entrypanel {
    String type;
    private Double id;
    //motorbike, car,truck,electric,van;
    String name;
     String vehicle_no;
     int mobile_no;
    Scanner scan = new Scanner(System.in);
    void entrypanel(String name,int mobile_no,String type,String vehicle_no) {
        this.name=name;
        this.mobile_no = mobile_no;
        this.type=type;
        this.vehicle_no=vehicle_no;

        System.out.println("welcome to the group 7 parkinglot space");
        id = Math.random();
        System.out.println("the customer id:" +id);
        System.out.println("customer name :"+name);
        System.out.println(" mobile_no of  the customer :"+mobile_no);
        System.out.println("vehicle type:"+type);
        System.out.println("enter the the vehicle no.:"+vehicle_no);
        System.out.println("current time: "+ LocalDateTime.now());
    }

}
class Farecontroller
{
    Scanner scan = new Scanner(System.in);
    int mobilenum,i=0,sum=0,sum1=0;
    double tot_hours;
    double bill;
    String str,credit;
    int hours ,minutes;
    // here in this method we calculate the total time the vehicle has been parked
    void input()
    {


        System.out.println("enter your mobile number:");
        mobilenum = scan.nextInt();
        System.out.println(" no. of hours the vehicle is parked");
        //using the class variables of ParkingSpot class , where we had found out the time difference between the time when the vehicle is parked and the time when it is removed
        tot_hours=(this.hours*60+this.minutes)/60.0;
    }
    //here in this method we are calculating bill based on number of hours the vehicle has been parked
    void calculate()
    {
        if(tot_hours<=1.00)
        {
            bill= tot_hours*20;
        }
        else if( tot_hours>1.00&& tot_hours<=3.00)
        {
            bill=20 +( tot_hours-1.00)*10;
        }
        else if( tot_hours>3.00)
        {
            bill= 40 +( tot_hours-3.00)*5;//(20+ 10 + 10 )
        }
        else
            System.out.println("wrong values of hours");
    }
    //in this method we display the bill that the customer has to pay
    void display()
    {
        System.out.println("mobile number is " + mobilenum);
        System.out.println("it is parked for" +tot_hours + " hours");
        System.out.println("Total amount to be paid is Rs." +bill);
    }
    // here we use this method to facilitate the customer to pay the money either through cash or through credit card
    void cashpayment()
    {
        System.out.println("enter the type of payment you want to pay  either credit or by cash");
        str=scan.next();
        if(str=="credit")
        {
            System.out.println("enter your credit card number:");
            credit=scan.next();
            //here when we enter credit card number , we have to find it out whether it is valid or not for that a simple logic has been written
            for(i=0;i<str.length();i++)
            {
                sum=sum+str.charAt(i);
            }
            for(i=0;i<str.length();i++)
            {
                if(i%2!=0)
                {
                    sum1=sum1+str.charAt(i);
                }
            }
            if((sum+sum1)%10==0)
            {
                System.out.println("your credit card number is valid");
                System.out.println("an amount of Rs" + bill + " is deducted from your account");
                System.out.println("transaction successfull");
            }
            else
                System.out.println("your credit card number is not valid try again");
        }
        if(str== "cash")
            System.out.println("an amount of Rs"+ bill+ " is to be be paid ");
    }
}



 class exitpanel extends Farecontroller {
    Double id;
    int ch;
    String str;
    void exitpanel() {
        id=Math.random();
        System.out.println("customer id"+id);
        System.out.println("total parking hours"+tot_hours);
        System.out.println("total bill "+bill);
        System.out.println("thanks for visiting our group 7 parkinglot");
        System.out.println("scale us from 1 to 4 based on your experience");
        Scanner sc = new Scanner(System.in);
        ch=sc.nextInt();
        switch(ch)
        {
            case 1:
                str="very good";
                break;
            case 2:
                str="good";
                break;
            case 3:
                str="bad";
                break;
            default:
                str="very bad";
        }
        if(str=="bad"||str=="very bad")
        {
            System.out.println("sorry for the inconvinience we will improve our performance");

        }
        else
            System.out.println("thanks for your reviews as "+str);
        System.out.println(" revisit again");
    }
}
