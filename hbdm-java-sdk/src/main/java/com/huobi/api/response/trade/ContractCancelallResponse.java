package com.huobi.api.response.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ContractCancelallResponse {
    /**
     * status : ok
     * data : {"errors":[{"order_id":"161251","err_code":200417,"err_msg":"invalid symbol"},{"order_id":161253,"err_code":200415,"err_msg":"invalid symbol"}],"successes":"161256,1344567"}
     * ts : 1490759594752
     */

    private String status;
    private String errCode;
    private String errMsg;
    private DataBean data;
    private Long ts;

    @Data
    @AllArgsConstructor
    public static class DataBean {
        private List<ErrorsBean> errors;
        private String successes;

        @Data
        @AllArgsConstructor
        public static class ErrorsBean {
            /**
             * order_id : 161251
             * err_code : 200417
             * err_msg : invalid symbol
             */

            @SerializedName("order_id")
            private String orderId;
            @SerializedName("err_code")
            private Integer errCode;
            @SerializedName("err_msg")
            private String errMsg;

        }
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}
    
}
