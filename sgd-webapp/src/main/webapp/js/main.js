;(function( window, document, $, undefined ) {
    "use strict";

    $(document).ready(function () {
      $(".tab-pane").hide();
      $(".tab-pane.active").show();
    });

    // Menu Toggle
    (function() {
      $(function() {

        var $toggle = $("#botonMenu");
        var $container = $(".navigation-menu");

        if( ! $toggle.length || ! $container.length ) return;

        $toggle.click(function() {
          $container.toggleClass("navigation-menu_mobile");
        });
      });
    })();

    // Cerrar Toggle
    (function() {
      $(function() {

        var $toggle = $("#botonCerrar");
        var $container = $(".navigation-menu");

        if( ! $toggle.length || ! $container.length ) return;

        $toggle.click(function() {
          $container.toggleClass("navigation-menu_mobile");
        });
      });
    })();

  })( window, document, jQuery );

  $(function(){
    $('.btn-circle').on('click', function(e){
      var tab = $(e.currentTarget).attr('href');
      var $activeTab = $('.tab-pane.active');
      var $activeButtonTab = $('.btn-circle.active');

      $activeButtonTab.removeClass('active');
      $(e.currentTarget).addClass('active');

      $(tab).addClass('active').show();
      $activeTab.removeClass('active').hide();
    });

    $('.next-step, .prev-step').on('click', function (e){
      var $activeTab = $('.tab-pane.active');
      var $activeMenu = $('.btn-circle.active');

      if ( $(e.target).hasClass('next-step') )
      {
         var nextTab = $activeTab.next('.tab-pane').attr('id');
         $('#'+ nextTab).addClass('active').show();
         $activeTab.removeClass('active').hide();

         $('#'+ nextTab+'-btn').addClass('active').show();
         $activeMenu.removeClass('active');
      }
      else
      {
         var prevTab = $activeTab.prev('.tab-pane').attr('id');
         $('#'+ prevTab).addClass('active').show();
         $activeTab.removeClass('active').hide();

         $('#'+ prevTab+'-btn').addClass('active').show();
         $activeMenu.removeClass('active');
      }
    });

    $('.agregar-button').on('click', function (e){
      $('.form.container').css('display', 'flex');
      $('.close-button').css('display', 'block');
    });

    $('.close-button').on('click', function (e){
      $('.form.container').css('display', 'none');
      $('.close-button').css('display', 'none');
    });

    $('.agregar-sug').on('click', function (e){
      var $inp = $(e.target).closest('.item').find('.sugerencia');
      $inp.css('display', 'flex');
    });

    $('.close-sug').on('click', function (e){
      var $inp = $(this).closest('.item.sugerencia');
      $inp.css('display', 'none');
    });

    $('.item-horas').on('change', function (e){
      var suma = 0;
      $(".item-horas").each(function(){
        suma = suma + parseInt($(this).val());
      })
      $('.horas-totales').val(suma);
      $('.creditos').val(Math.round(parseInt($('.horas-totales').val())/15));

    });

    $('.horas-totales, .creditos').on('change', function (e){
      var suma = 0;
      $(".item-horas").each(function(){
        suma = suma + parseInt($(this).val());
      })

      if ($(e.target).hasClass('horas-totales')){
        $('.creditos').val(Math.round(parseInt($('.horas-totales').val())/15));
      } else {
        $('.horas-totales').val(parseInt($('.creditos').val())*15);
      }

      var sumaHoras = suma == $('.horas-totales').val();

      if(!sumaHoras){
        $('.mensaje').text('La suma de horas no es correcta');
        $('.mensaje').css('display', 'block');
      } else {
        $('.mensaje').text('');
        $('.mensaje').css('display', 'none');
      }
   });

   $(".item-horas").on('change', function validaHoras(e){
    var suma = 0;
      $(".item-horas").each(function(){
        suma = suma + parseInt($(this).val());
      })
      var sumaHoras = suma == $('.horas-totales').val();

      if(!sumaHoras){
        $('.mensaje').text('La suma de horas no es correcta');
        $('.mensaje').css('display', 'block');
      } else {
        $('.mensaje').text('');
        $('.mensaje').css('display', 'none');
      }
   });

  // Collapse click
  $('[data-toggle=sidebar-colapse]').click(function() {
      SidebarCollapse();
  });

  function SidebarCollapse () {
      $('.menu-collapsed').toggleClass('d-none');
      $('#sidebar-container').toggleClass('sidebar-expanded sidebar-collapsed');
      $('#content').toggleClass('content-expanded content-collapsed');
      $('.side-bar .group').toggleClass('d-none');
      $('#collapse-icon').toggleClass('fa-angle-double-left fa-angle-double-right');
  };

  $(".datepicker").datepicker({
          format: 'dd/mm/yy',
          dateFormat: 'dd/mm/yy'
      });

      $.datepicker.regional['es'] = {
   monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
   monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
   dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
   dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
   dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá']
   };

   $.datepicker.setDefaults($.datepicker.regional['es']);
  })
