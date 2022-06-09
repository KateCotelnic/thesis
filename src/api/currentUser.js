import request from "@/helpers/interseptorSetup";

export const getCurrentUser = () => {
  return request({
    url: `/details`,
    method: "get",
  });
};