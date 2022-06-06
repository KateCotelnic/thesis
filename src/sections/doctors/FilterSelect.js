import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const FilterSelect = ({ onChange, values, selectedValue, label }) => {
  return (
    <div>
      <FormControl variant="standard" sx={{ minWidth: '25ch' }}>
        <InputLabel shrink>{label}</InputLabel>
        <Select
          displayEmpty
          value={selectedValue}
          onChange={onChange}
          label={label}
        >
          {values.map((value) => {
            return (
              <MenuItem key={value} value={value}>
                {value}
              </MenuItem>
            );
          })}
        </Select>
      </FormControl>
    </div>
  );
};

export default FilterSelect;
