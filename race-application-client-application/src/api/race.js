import apiOrigin from "./api";

export const getAllRaces = async (authToken) => {
    return await fetch(`${apiOrigin}/v1/races`, {
      headers: {
        Authorization: "Bearer " + authToken
      },
    }).then((res) => res.json());
  };