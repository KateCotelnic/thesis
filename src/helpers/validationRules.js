export const emailValidation = [
  (input) =>
    /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,7})+$/.test(input) ||
    "Please enter a valid email address e.g. example@example.com",
];

export const passwordValidation = [
  (input) => !!input || "Please enter your password",
];

export const confirmPasswordValidation = (password, confirmPasword) => {
  if (password !== confirmPasword) {
    return "Password confirmation failed";
  } else {
    return true;
  }
};
