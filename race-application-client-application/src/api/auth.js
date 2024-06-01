import apiOrigin from "./api";

export const signIn = (user) => {
  return fetch(`${apiOrigin}/v1/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  }).then((res) => res.json());
};