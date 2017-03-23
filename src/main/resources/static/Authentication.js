/**
 * Created by obinnaelobi on 3/13/2017.
 */


$(document).ready(function() {


    $('#myTabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        e.target // newly activated tab
        e.relatedTarget // previous active tab
    })
});

function signup(){

    var userType;
if(document.getElementById('rad1').checked) {
    userType = $("#rad1").val();
}else if(document.getElementById('rad2').checked) {
    userType = $("#rad2").val();
}else if(document.getElementById('rad3').checked) {
    userType = $("#rad3").val();
}

    $.post('/register', { firstName : "" + $("#firstName").val(),  lastName : "" + $("#lastName").val(), email: "" + $("#signupEmail").val(),  password : "" + $("#signupPassword").val(), userType : userType});


}