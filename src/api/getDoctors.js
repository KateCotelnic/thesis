import request from "@/helpers/interseptorSetup";

export const fetchDoctors = (params) => {
  return request({
    url: `/admin/doctors`,
    method: "get",
    params,
  });
};
