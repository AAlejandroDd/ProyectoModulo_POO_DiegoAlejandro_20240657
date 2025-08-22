package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Models;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ApiResponse <T>{
    private boolean success;
    private boolean succes;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse <T> succes (T data)
    {
        return new ApiResponse<>(true, "operacion exitosa", data);
    }

    public static <T> ApiResponse <T> succes (String message, T data){
        return new ApiResponse<>(true, message, data);
    }

    public static ApiResponse<?> error(String message)
    {
        return new ApiResponse<>(false, message, null);
    }
}
