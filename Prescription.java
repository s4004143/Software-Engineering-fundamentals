import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;   
import java.io.IOException; 
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Prescription {

    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes= {"Client", "Optometrist"}; //options for remark types
    private ArrayList <String> postRemarks= new ArrayList<>();
    private int remarks = 2; //all prescriptions initally have 2 remarks, adding 1 remark reduces total remarks


    // set constructor 
    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis, float cylinder, String examinationDate, String optometrist){
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            // Parse the string to a Date object
            Date date = sdf.parse(examinationDate);
            
            // Convert the Date object back to string
            String formattedDate = sdf.format(date);
            
            // Check if the formatted date matches the original string
            if(examinationDate.equals(formattedDate)){
                this.examinationDate = date;
            }
            else{
                System.out.println("Please input valid date");
                this.examinationDate = null;
            }
        } 

        catch (ParseException e) {
            //if date does not match
            System.out.println("Invalid date format");
            this.examinationDate = null;
            
        }

        
        this.optometrist = optometrist;
        
        
        

    }

    public boolean addPrescription()
    {
        //if name is in length
        if ((this.firstName.length() >= 4) && (this.firstName.length() <= 15) && (this.lastName.length() >= 4) && (this.lastName.length() <= 15)){
            if (this.address.length() < 20){ //if address is less than 20 chracters
                if ((this.sphere >= -20.0) && (this.sphere <= 20.0 )){ //if value of sphere in parameters
                    if ((this.cylinder >= -4.0) && (this.cylinder <= 4.0 )){//if value of cylinder in parameters
                        if ((this.axis >= 0) && (this.axis <= 180)){//if value of axis in parameters
                            //date format check

                                    if (this.examinationDate != null){
                                    

                                    
                                //if optometrist length in parameters
                                if ((optometrist.length() >= 8) && (optometrist.length() <= 25)){

                                    try {
                                        //open the file, with filename
                                        
                                        FileWriter myWriter = new FileWriter("presc.txt");
                                        //start writing into file
                                        //change examination date to string to write into txt
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                                        String printableDate = sdf.format(examinationDate);
                                        myWriter.write(printableDate + ": ");
                                        myWriter.write(this.firstName + " " + this.lastName + " - ");
                                        myWriter.write("Address: " + this.address + " Sphere: "+ this.sphere + " cylinder: " + this.cylinder +" axis: " + this.axis + " Optometrist: " + this.optometrist);
                                        //myWriter.write()
    
                                        myWriter.close(); // close the file
                                        System.out.println("Successfully wrote to the file.");
                                      } catch (IOException e) {
                                        //if it doesnt open...
                                        System.out.println("An error occurred.");
                                        e.printStackTrace();
                                      }
    
                                    
    
                                      //return true
                                    return true;
                                }
                            }
                            else{
                                System.out.println("empty date");
                            }
                            

                        }
                        else{
                            System.out.println("Invalid axis");
                        }
                        }
                        else{
                            System.out.println("Invalid cylinder");
                        }
                        }
                        else{
                            System.out.println("Invalid sphere");
                        }
                    }
                    else{
                        System.out.println("Invalid address");
                    }
                }
                else{
                    System.out.println("Invalid name");
                }
                //return false
                return false;
                }
            
        

    public boolean addRemark(String remark, String remarkType)
    {
        if ((remark.length() >= 6) && (remark.length() <= 20)){ //checking if remark is in length parameters
            if (Character.isUpperCase(remark.charAt(0))){//checking if remark is in the valid names of remarkTypes
                if(remarks > 0){
                    remarks = remarks - 1; //decrement remark by 1
                    if (remarkType.contains(remarkType)){


                        try {
                            //open the file, with filename
                            FileWriter myWriter = new FileWriter("review.txt");
                            //start writing into file
                      
                            myWriter.write(remarkType + ": " + remark);
                            //myWriter.write()

                            myWriter.close(); // close the file
                            System.out.println("Successfully wrote to the file.");
                          } catch (IOException e) {
                            //if it doesnt open...
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                          }


                    }
                    else{
                        System.out.println("Wrong remark Type");
                    }
                    

                }
                else{
                    System.out.println("no more remark space");
                }
            }
            else{
                System.out.println("remark needs first capital letter");
            }
    
        }
        else{
        System.out.println("remark needs to be between 6 to 20 characters");
        }

        return true;
    }


    // set all getters
    public int getPrescID(){

        return prescID;
    }

    public String getFirstName(){

        return firstName;
    }

    public String getLastName(){

        return lastName;
    }

    public float getSphere(){

        return sphere;
    }

    public float getAxis(){

        return axis;
    }

    public float getCylinder(){

        return cylinder;
    }

    public Date getExaminationDate(){

        return examinationDate;
    }

    public String[] getRemarkTypes(){

        return remarkTypes;
    }

    public ArrayList <String> getPostRemarks(){

        return postRemarks;
    }

  public static void main(String[] args) {
    
    
    Prescription test = new Prescription(10,"Kevin", "Smith", "Building 80 RMIT", 3, 120, 4, "24/12/24", "Halil Ali");
    test.addPrescription();
    test.addRemark("This sucks so much", "Client");
  }

}