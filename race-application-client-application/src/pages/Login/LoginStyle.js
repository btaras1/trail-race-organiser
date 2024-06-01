import styled from "styled-components";

import { breakpoints } from "../../lib/style/theme";

export const LoginWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  height: 100%;
  width: 100%;
`;

export const Logo = styled.img`
  width: 100%;
  height: 100%;
  
`;

export const Inner = styled.div`
  width: 100%;
  padding: 16px;
  @media screen and (${breakpoints.tablet}) {
    width: 232px;
  }
`;
