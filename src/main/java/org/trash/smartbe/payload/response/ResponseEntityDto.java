package org.trash.smartbe.payload.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResponseEntityDto {

    private static final String SUCCESSFUL = "successful";
    private static final String UNSUCCESSFUL = "unsuccessful";

    private String status;
    private List<Object> results;

    // Default constructor initializing status and results
    public ResponseEntityDto() {
        status = SUCCESSFUL;
        results = new ArrayList<>();
    }

    // Constructor for message and unsuccessful status
    public ResponseEntityDto(String message, boolean unsuccessful) {
        this();
        this.status = unsuccessful ? UNSUCCESSFUL : SUCCESSFUL;
        putToResults(new Acknowledgement(message));
    }

    // Constructor for single data object with unsuccessful status
    public ResponseEntityDto(boolean unsuccessful, Object data) {
        this();
        status = unsuccessful ? UNSUCCESSFUL : SUCCESSFUL;
        putToResults(data);
    }

    // Constructor for multiple data objects with unsuccessful status
    public ResponseEntityDto(boolean unsuccessful, List<Object> data) {
        this();
        status = unsuccessful ? UNSUCCESSFUL : SUCCESSFUL;
        putToResults(data);
    }

    // Helper method to add data to results
    protected void putToResults(Object data) {
        if (data != null) {
            if (data instanceof Collection<?>) {
                results.addAll((Collection<?>) data);
            } else {
                results.add(data);
            }
        }
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

    // Acknowledgement class for messages
    public static class Acknowledgement {
        private String message;

        public Acknowledgement(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
