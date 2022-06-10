import * as React from 'react';
import axios from 'axios';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { api } from '../api';


function updatePass(oldP, newP) {
  const body = {
    oldPassword: oldP,
    newPassword: newP
  }
  // console.log(body);
  axios.post(api.dashboard.postChangePassword(), JSON.stringify({
    "oldPassword": oldP,
    "newPassword": newP
  }))
    .then((response) => console.log(response))
    .catch((error) => {
      // console.log(error);
    });
}

export default function FormDialog({setOpen}) {
  const [oldPassword, setOldP] = React.useState("");
  const [newPassword, setNewP] = React.useState("");

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    window.location.reload()
  };

  const handleOldPassChange= (event) => {
    setOldP(event);
  }

  const handleNewPassChange= (event) => {
    setNewP(event);
    // console.log(event)
  }

  const accessToken = localStorage.getItem("token");

  const handleSubmit = async (e) => {

    const body = {
      oldPassword,
      newPassword
    }
    // console.log(body)
    e.preventDefault();

    try {
      const response = await axios.post(api.dashboard.postChangePassword(), body,
        {headers: {"Authorization": accessToken}}
      );
      // console.log(response);
    } catch (err) {
      // console.log(err)
    }
    handleClose();
  }

  return (
    <div>
      <Dialog onClose={handleClose} open={setOpen}>
        <DialogTitle onClose={handleClose} >Change password</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Old password"
            type="password"
            fullWidth
            variant="standard"
            onChange={(e) => setOldP(e.target.value)}
            value={oldPassword}
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="New password"
            type="password"
            fullWidth
            variant="standard"
            onChange={(e) => setNewP(e.target.value)}
            value={newPassword}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => {setOpen(false)}} >Cancel</Button>
          <Button onClick={handleSubmit}>Save</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
