import Swal from "sweetalert2";

const sendSwal = (icon, alertMsg) => (alertMsg) ? Swal.fire({
    icon: "success",
    title: `<span style="font-family: 'NanumSquare'"}">${alertMsg}</span>`,
    showConfirmButton: false,
    timer: 1000,
}) : null;

export { sendSwal };