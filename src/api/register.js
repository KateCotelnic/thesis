import request from "@/helpers/interseptorSetup";

export const signUp = (data) => {
  return request({
    url: `/register`,
    method: "post",
    data,
  });
};
