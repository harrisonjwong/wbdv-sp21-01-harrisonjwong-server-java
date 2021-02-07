(function () {
    var $usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld;
    var $removeBtn, $editBtn;
    var $updateBtn, $createBtn, $searchBtn;
    var $tbody;
    var userService = new AdminUserServiceClient();
    var users;
    var selectedUser;
    $(main);

    function main() {
        // contains all users shown on screen
        users = [];
        // contains the currently selected user
        selectedUser = null;
        // second row fields
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');

        // second row buttons (search button has no functionality)
        $updateBtn = $('.wbdv-update');
        $createBtn = $('.wbdv-create');
        $searchBtn = $('.wbdv-search');
        $createBtn.click(createUser);
        $updateBtn.click(updateUser);

        // contains the table body
        $tbody = $('.wbdv-tbody');

        // gets users from the server, sets them to the users variable, then renders them
        findAllUsers()
    }

    function userRowTemplate(user) {
        return `<tr>
                    <td>${user.username}</td>
                    <td></td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role}</td>
                    <td>
                        <span class='pull-right wbdv-nowrap-right-align'>
                            <button class='btn btn-light fas fa-times wbdv-remove' id='remove-${user._id}'/>
                            <button class='btn btn-light fas fa-pencil wbdv-edit' id='edit-${user._id}'/>
                        </span>
                    </td>
                </tr>`;
    }

    function clearFields() {
        $usernameFld.val('')
        $passwordFld.val('')
        $firstNameFld.val('')
        $lastNameFld.val('')
        $roleFld.val('FACULTY')
    }

    function createUser() {
        const localUser = {
            username: $usernameFld.val(),
            password: $passwordFld.val(),
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            role: $roleFld.val()
        };
        userService.createUser(localUser).then((userFromServer) => {
            users.push(userFromServer);
            renderUsers(users);
        })
        clearFields();
    }

    function deleteUser(event) {
        let deleteBtn = $(event.target);
        let id = deleteBtn.attr('id');
        id = id.substring('remove-'.length);
        users = users.filter((user) => user._id !== id);
        userService.deleteUser(id).then(() => {
            renderUsers(users);
        });
    }

    function selectUser(event) {
        let editBtn = $(event.target);
        let id = editBtn.attr('id');
        id = id.substring('edit-'.length);
        selectedUser = users.find((user) => user._id === id);
        $usernameFld.val(selectedUser.username);
        $passwordFld.val(selectedUser.password);
        $firstNameFld.val(selectedUser.firstName);
        $lastNameFld.val(selectedUser.lastName);
        $roleFld.val(selectedUser.role);
    }

    function updateUser() {
        if (selectedUser) {
            selectedUser.username = $usernameFld.val();
            selectedUser.password = $passwordFld.val();
            selectedUser.firstName = $firstNameFld.val();
            selectedUser.lastName = $lastNameFld.val();
            selectedUser.role = $roleFld.val();
            userService.updateUser(selectedUser._id, selectedUser).then(() => {
                // since the selectedUser object is already in the users array, don't need to update it again
                renderUsers(users);
                selectedUser = null;
                clearFields();
            });
        } else {
            alert('Please select a user before clicking the update button.')
        }
    }

    function renderUsers(users) {
        $tbody.empty();
        for (let i = 0; i < users.length; i++) {
            $tbody.append(userRowTemplate(users[i]))
        }
        $removeBtn = $('.wbdv-remove').click(deleteUser);
        $editBtn = $('.wbdv-edit').click(selectUser);
    }

    function findAllUsers() { // optional - might not need this
        userService.findAllUsers().then((actualUsers) => {
            users = actualUsers;
            renderUsers(users);
        });
    }

    function findUserById() { // optional - might not need this
        return userService.findUserById(selectedUser._id).then(user => user);
    }
})();
