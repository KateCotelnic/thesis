import { faker } from '@faker-js/faker';
import { sample } from 'lodash';

// ----------------------------------------------------------------------

const users = [...Array(24)].map((_, index) => ({
  id: faker.datatype.uuid(),
  avatarUrl: `/static/mock-images/avatars/avatar_${index + 1}.jpg`,
  name: faker.name.findName(),
  company: sample([
    '069984550',
    '069284150',
    '068084412',
    '069584623',
    '069384578',
    '069284945',
  ]),
  isVerified: sample([
    '45 min',
    '30 min',
    '60 min',
    '25 min',
    '15 min',
    '35 min',
  ]),
  visit: sample([
    '10:00',
    '9:30',
    '15:00',
    '12:25',
    '12:00',
    '13:30',
  ]),
  status: sample(['approved', 'requested', 'declined', 'done']),
  role: sample([
    '12.05.2022',
    '12.05.2022',
    '13.05.2022',
    '16.05.2022',
    '18.05.2022',
    '19.05.2022',
  ]),

}));

export default users;
