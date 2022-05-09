package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
    public class ResponseBodyPojo {

        private String status;
        private Dummy_API_Data_Pojo data;
        private String message;

        /**
         * No args constructor for use in serialization
         *
         */
        public ResponseBodyPojo() {
        }

        /**
         *
         * @param data
         * @param message
         * @param status
         */
        public ResponseBodyPojo(String status, Dummy_API_Data_Pojo data, String message) {
            super();
            this.status = status;
            this.data = data;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Dummy_API_Data_Pojo getData() {
            return data;
        }

        public void setData(Dummy_API_Data_Pojo data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    @Override
    public String toString() {
        return "ResponseBodyPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
