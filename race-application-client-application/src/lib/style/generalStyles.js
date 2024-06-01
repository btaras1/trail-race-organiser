import styled from "styled-components";
import { colors, breakpoints } from "../../lib/style/theme";


export const Main = styled.main`
  height: 100%;
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  width: 100%;
  @media screen and (${breakpoints.mobileLarge}) {
    width: 400px;
    margin: 0 auto;
  }
`;

export const FormRow = styled.div`
  margin-bottom: 32px;
  &:last-child {
    margin-bottom: 0;
  }
`;

export const InputLabel = styled.label`
  font-size: 14px;
  display: block;
  font-weight: 600;
  margin-bottom: 4px;
  @media screen and (${breakpoints.desktop}) {
    font-size: 16px;
  }
`;

export const InputText = styled.input`
  border: 1px solid ${colors.lightGrey};
  border-radius: 6px;
  width: 100%;
  line-height: 40px;
  padding: 0 10px;
  outline: none;
  font-size: 14px;
  font-family: "Montserrat", sans-serif;

  &:focus {
    border-color: ${colors.yellow};
  }
  @media screen and (${breakpoints.desktop}) {
    font-size: 16px;
  }
`;

export const InputError = styled.p`
  font-size: 14px;
  color: ${colors.red};
  padding-top: 8px;
`;

export const SuccessMessage = styled.p`
  margin: 0 auto;
  padding: 15px;
  margin-bottom: 32px;
  border-radius: 6px;
  background: ${colors.successBackground};
  color: ${colors.success};
  ${(props) =>
    props.isError &&
    `
        background: ${colors.errorBackground};
        color: ${colors.error};
    `};
  @media screen and (${breakpoints.mobileLarge}) {
    max-width: 400px;
  }
`;
