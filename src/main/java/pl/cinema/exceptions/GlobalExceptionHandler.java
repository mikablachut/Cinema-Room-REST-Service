package pl.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CinemaSeatNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleCinemaSeatNotFoundException(CinemaSeatNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("The number of a row or a column is out of bounds!");
        return errorResponse;
    }

    @ExceptionHandler(CinemaPurchaseNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleCinemaPurchaseNotFoundException(CinemaPurchaseNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("The ticket has been already purchased!");
        return errorResponse;
    }

    @ExceptionHandler(CinemaExpireTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleCinemaExpireTokenException(CinemaExpireTokenException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Wrong token!");
        return errorResponse;
    }

    @ExceptionHandler(CinemaPasswordNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleCinemaPasswordNotFoundException(CinemaPasswordNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("The password is wrong!");
        return errorResponse;
    }
}
