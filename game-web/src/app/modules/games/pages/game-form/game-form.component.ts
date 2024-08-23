import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { take } from 'rxjs';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { GameService } from '../../../../core/services/game/game.service';
import { TranslateConfigModule } from '../../../../shared/modules/translate-config/translate-config.module';

@Component({
  selector: 'app-game-form',
  standalone: true,
  imports: [
    ReactiveFormsModule, 
    RouterModule,
    TranslateModule,
    TranslateConfigModule,
  ],
  templateUrl: './game-form.component.html',
})
export class GameFormComponent implements OnInit {
  protected form!: FormGroup;
  protected showErrors: boolean = false;
  protected image: any;
  protected imgUrl: any;
  protected idGame!: number;

  constructor(
    protected router: Router,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder,
    protected translate: TranslateService,
    protected toast: ToastrService,
    protected gameService: GameService
    
  ) {}

  ngOnInit(): void {
    this.translate.setDefaultLang('es');
    
    /* this.form = new FormGroup({
      id: new FormControl('', [Validators.required, Validators.maxLength(50)], []),
      title: new FormControl('', [Validators.required, Validators.maxLength(50)], []),
      platform: new FormControl('', [Validators.required, Validators.maxLength(50)], []),
      category: new FormControl('', [Validators.required, Validators.maxLength(50)], []),
      year: new FormControl(null, [Validators.required], []),
      description: new FormControl('', [Validators.maxLength(500)], []),
    }); */

    // El FormBuilder simplifica el código
    this.form = this.fb.group({
      id: [''],
      title: ['', [Validators.required, Validators.maxLength(50)]],
      platform: ['', [Validators.required, Validators.maxLength(50)]],
      category: ['', [Validators.required, Validators.maxLength(50)]],
      year: [ null, [Validators.required]],
      description: ['', [Validators.maxLength(500)]],
    });
    this.findGameById();
  }

  /**
   * Nuevo registro
   */
  public saveGame(): void {
    this.gameService
      .saveGame(this.form.value)
      .pipe(take(1))
      .subscribe({
        next: (res) => {
          if (res) {
            this.router.navigate(['/juegos']);
            this.translate.get('createOk').subscribe((msg: string) => {
              this.toast.success(msg);
            });
          } else {
            this.translate.get('error').subscribe((msg: string) => {
              this.toast.error(msg);
            });
          }
        },
        error: () => {
          this.translate.get('error').subscribe((msg: string) => {
            this.toast.error(msg);
          });
        }
      });
  }

  /**
   * Obtener registro por el id
   */
  public findGameById(): void {
    const id = 'id';
    this.idGame = this.activatedRoute.snapshot.params[id];
    if (this.idGame) {
      this.gameService
        .findGameById(this.idGame)
        .pipe(take(1))
        .subscribe({
          next: (res) => {
            if (res.id) {
              this.form.patchValue({
                id: res.id,
                title: res.title,
                platform: res.platform,
                category: res.category,
                year: res.year,
                description: res.description,
              });
            } else {
              this.translate.get('error').subscribe((msg: string) => {
                this.toast.error(msg);
              });
            }
          },
          error: () => {
            this.translate.get('error').subscribe((msg: string) => {
              this.toast.error(msg);
            });
          }
        });
    }
  }

  /**
   * Actualizar registro
   */
  public updateGame(): void {
    this.gameService
      .updateGame(this.form.value)
      .pipe(take(1))
      .subscribe({
        next: (res) => {
          if (res) {
            this.router.navigate(['/juegos']);
            this.translate.get('updateOk').subscribe((msg: string) => {
              this.toast.success(msg);
            });
          } else {
            this.toast.error(res);
          }
        },
        error: () => {
          this.translate.get('error').subscribe((msg: string) => {
            this.toast.error(msg);
          });
        }
      });
  }

  /**
   * Valida los campos del formulario
   */
  public validField(field: string) {
    if (!this.form.get(field)?.valid && this.form.get(field)?.touched) {
      return false;
    }
    return true;
  }
}
