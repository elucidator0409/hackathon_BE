Login
Open your web browser and navigate to the application URL: /login

Enter your credentials:

Username: atcadmin
Password: atc2040go
Click the "Login" button to log in to your account.



Posting News
After logging in, you will be redirected to the dashboard.

Look for a "News&Event" tab and click on it.

Now we at URL : /admin/blog => create news here




SQL:

For create account:
INSERT INTO hackathon.member (mbr_id, mbr_pw) VALUES ('', '');

Then set role for account with mbr_pk from member table:
INSERT INTO hackathon.member_role (mbr_id, role_name) VALUES (<## mbr_pk here ##>, 'ADMIN');

