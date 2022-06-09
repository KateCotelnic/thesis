import request from "@/helpers/interseptorSetup";

export const signIn = (data) => {
  return request({
    url: `/login`,
    method: "post",
    data,
  });
};
