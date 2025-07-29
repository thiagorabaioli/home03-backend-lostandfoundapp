package tfr.LostAndFoundAPP.services.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message){
        super(message);
    }



}
