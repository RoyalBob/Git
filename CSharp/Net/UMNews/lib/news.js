$(function () {
    var slidarIsOpen = true;
    $('.sidebar-toggle').click(function () {
        if (slidarIsOpen) {
            slidarIsOpen = false;
            $('.sidebar-toggle-line:nth-child(1)').css({ 'width': '100%', 'top': '5px', 'transform': 'rotateZ(-45deg)', 'opacity': '1', 'left': '0' });
            $('.sidebar-toggle-line:nth-child(2)').css({ 'opacity': '0' })
            $('.sidebar-toggle-line:nth-child(3)').css({ 'width': '100%', 'top': '-5px', 'transform': 'rotateZ(45deg)', 'opacity': '1', 'left': '0' });
            $('aside').animate({ width: '320px' },'fast','linear');      
            $('body').animate({ width: '-=320px', marginLeft: '320px' }, 'fast', 'linear');
            setTimeout(function () {
                $('.sidebar-inner').fadeToggle('slow', 'linear');

            },500);

        } else {
            slidarIsOpen = true;
            $('.sidebar-toggle-line:nth-child(1)').css({ 'width': '100%', 'top': '0px', 'transform': 'rotateZ(0deg)', 'opacity': '1', 'left': '0' });
            $('.sidebar-toggle-line:nth-child(2)').css({ 'opacity': '1' })
            $('.sidebar-toggle-line:nth-child(3)').css({ 'width': '100%', 'top': '0px', 'transform': 'rotateZ(0deg)', 'opacity': '1', 'left': '0' });
            $('aside').animate({ width: '0px' },'fast','linear');
            $('body').animate({ width: '100%', marginLeft: '0%' }, 'fast', 'linear');
            $('.sidebar-inner').fadeToggle(1, 'linear');

        }
    }).mouseenter(function () {
        if (slidarIsOpen) {
            
            $('.sidebar-toggle-line:nth-child(1)').css({ 'width': '50%', 'top': '2px', 'transform': 'rotateZ(-45deg)', 'opacity': '1', 'left': '0' });
            $('.sidebar-toggle-line:nth-child(3)').css({ 'width': '50%', 'top': '-2px', 'transform': 'rotateZ(45deg)', 'opacity': '1', 'left': '0' });
        }
    }).mouseleave(function () {
        if (slidarIsOpen) {
            $('.sidebar-toggle-line:nth-child(1)').css({ 'width': '100%', 'top': '0px', 'transform': 'rotateZ(0deg)', 'opacity': '1', 'left': '0' });
            $('.sidebar-toggle-line:nth-child(3)').css({ 'width': '100%', 'top': '0px', 'transform': 'rotateZ(0deg)', 'opacity': '1', 'left': '0' });
        }
    });


    $('.sidebar-nav li').click(function () {
        if (!$(this).hasClass('sidebar-nav-active')){
        $(this).addClass('sidebar-nav-active').siblings().removeClass('sidebar-nav-active');      
        $('.sidebar-sign-signin').toggleClass('visibility');
        $('.sidebar-sign-signup').toggleClass('visibility');
        if (!$('.sidebar-main').is(':hidden')) {
            if ($('.sidebar-main-nav-info').hasClass('sidebar-nav-active')) {
                $('.sidebar-info').siblings('.slide-page').hide();
                setTimeout(function () {
                    $('.sidebar-info').slideDown('slow');
                }, 100);
            }
            if ($('.sidebar-main-nav-manage').hasClass('sidebar-nav-active')) {
                $('.sidebar-manage').siblings('.slide-page').hide();
                setTimeout(function () {
                    $('.sidebar-manage').slideDown('slow');
                }, 100);
            }
            if ($('.sidebar-main-nav-admin').hasClass('sidebar-nav-active')) {
                $('.sidebar-admin').siblings('.slide-page').hide();
                setTimeout(function () {
                    $('.sidebar-admin').slideDown('slow');
                }, 100);
            }
        }
        }
    });
});

$(function () {
    $('.discuss-unlock').click(function () {
        $('.discuss-lock').remove();
        $('.content-speak-submit').css({ 'top': '0' });
    });
})





