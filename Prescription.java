import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;   
import java.io.IOException; 
import java.text.SimpleDateFormat;

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
    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist){
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
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
                            //date format set
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); 
                            sdf.setLenient(false);
      
                                //if optometrist length in parameters
                                if ((optometrist.length() >= 8) && (optometrist.length() <= 25)){

                                    try {
                                        //open the file, with filename
                                        FileWriter myWriter = new FileWriter("presc.txt");
                                        //start writing into file
                                        myWriter.write("Start");
                                        myWriter.write(this.firstName + " " + this.lastName);
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
                                else{
                                    System.out.println("Invalid optometrist");
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
                            myWriter.write("Start");
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
        System.out.println("remark needs to be between 6 to 20 characters");


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
    
    Date mydate = new Date(12,12,2004); 
    
    Prescription test = new Prescription(10,"first", "last", "AddressSss", 3, 120, 4, mydate, "nowkawdm");
    test.addPrescription();
    test.addRemark("This sucksss", "Client");
  }

}