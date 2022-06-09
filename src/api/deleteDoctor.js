import request from "@/helpers/interseptorSetup";

export const deleteDoctor = (params) => {
  return request({
    url: `/admin/doctor?email=${params}`,
    method: "delete",
  });
};
