$(document).ready(function()
{
    $(".close").css("display", "none");
 
    var isMenuOpen = false;
 
    $('.open').click(function()
    {
        if (isMenuOpen == false)
        {
        //alert('je suis dans le bon cas')
            $("nav").clearQueue().animate({
                right : '0'
            })
            $("#content").clearQueue().animate({
                "margin-left" : '-280px'
            })
             
            $(this).fadeOut(200);
            $(".close").fadeIn(300);
             
            isMenuOpen = true;
        }
    });
     
    $('.close').click(function()
    {
        if (isMenuOpen == true)
        {
            $("nav").clearQueue().animate({
                right : '-280px'
            })
            $("#content").clearQueue().animate({
                "margin-left" : '0px'
            })
             
            $(this).fadeOut(200);
            $(".open").fadeIn(300);
             
            isMenuOpen = false;
        }
    });
});