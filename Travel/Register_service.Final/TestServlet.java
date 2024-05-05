package MyPack;                                                                                                         
                                                                                                                        
import org.junit.Test;                                                                                                  
import static org.junit.Assert.assertTrue;                                                                              
import static org.junit.Assert.assertFalse;                                                                             
import org.springframework.mock.web.MockHttpServletRequest;                                                             
import org.springframework.mock.web.MockHttpServletResponse;                                                            
                                                                                                                        
public class TestServlet{                                                                                               
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidNameValidName() {                                                                            
        Test_class Test_class = new Test_class();                                                                       
        assertTrue(Test_class.isValidName("Hello_there"));                                                              
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidNameInvalidNameWithSpecialCharacters() {                                                     
    	Test_class Test_class = new Test_class();                                                                       
        assertFalse(Test_class.isValidName("Hello_there@hey"));                                                         
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidNameInvalidNameWithLongName() {                                                              
    	Test_class Test_class = new Test_class();                                                                       
        assertFalse(Test_class.isValidName("ThisIsAVeryLongNameThatExceedsTheMaximumAllowedLength"));                   
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidEmailValidEmail() {                                                                          
    	Test_class Test_class = new Test_class();                                                                       
        assertTrue(Test_class.isValidEmail("test@example.com"));                                                        
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidEmailInvalidEmailWithSpecialCharacters() {                                                   
    	Test_class Test_class = new Test_class();                                                                       
        assertFalse(Test_class.isValidEmail("test@example#com"));                                                       
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidEmailInvalidEmailWithLongEmail() {                                                           
    	Test_class Test_class = new Test_class();                                                                       
        assertFalse(Test_class.isValidEmail("thisisaverylongemailaddress@example.com"));                                
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidPasswordValidPassword() {                                                                    
    	Test_class Test_class = new Test_class();                                                                       
        assertTrue(Test_class.isValidPassword("Password123"));                                                          
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidPasswordInvalidPasswordWithNoUppercase() {                                                   
    	Test_class Test_class = new Test_class();                                                                       
        assertFalse(Test_class.isValidPassword("password123"));                                                         
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testIsValidPasswordInvalidPasswordWithNoDigit() {                                                       
    	Test_class Test_class = new Test_class();                                                                       
        assertFalse(Test_class.isValidPassword("Password"));                                                            
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testDoPostValidInput() {                                                                                
    	Test_class Test_class = new Test_class();                                                                       
        MockHttpServletResponse response = new MockHttpServletResponse();                                               
        Test_class.doPost(new MockHttpServletRequest(), response);                                                      
        assertTrue(response.getContentAsString().contains("Signup done successfully!"));                                
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testDoPostInvalidInput() {                                                                              
    	Test_class Test_class = new Test_class();                                                                       
        MockHttpServletResponse response = new MockHttpServletResponse();                                               
        Test_class.doPost(new MockHttpServletRequest(), response);                                                      
        assertTrue(response.getContentAsString().contains("Signup failed!"));                                           
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testDoPostDatabaseConnectionFailure() {                                                                 
    	Test_class Test_class = new Test_class();                                                                       
    	Test_class.setDatabaseConnectionFailure(true);                                                                  
        MockHttpServletResponse response = new MockHttpServletResponse();                                               
        Test_class.doPost(new MockHttpServletRequest(), response);                                                      
        assertTrue(response.getContentAsString().contains("Database connection failed!"));                              
    }                                                                                                                   
                                                                                                                        
    @Test                                                                                                               
    public void testDoPostExistingEmail() {                                                                             
    	Test_class Test_class = new Test_class();                                                                       
        Test_class.setExistingEmail(true);                                                                              
        MockHttpServletResponse response = new MockHttpServletResponse();                                               
        Test_class.doPost(new MockHttpServletRequest(), response);                                                      
        assertTrue(response.getContentAsString().contains("Email already exists!"));                                    
    }                                                                                                                   
}                                                                                                                       
                                                                                                                        